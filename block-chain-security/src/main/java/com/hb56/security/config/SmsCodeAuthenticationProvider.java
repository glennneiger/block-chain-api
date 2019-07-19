package com.hb56.security.config;


import com.hb56.common.result.RestInfo;
import com.hb56.resource.owner.SecurityUserDetails;
import com.hb56.security.dao.SecurityUserFeign;
import com.hb56.security.model.SysRole;
import com.hb56.security.model.SysUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Been
 */

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserFeign userFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code = (String) authentication.getCredentials();
        String phoneNumber = authentication.getName();
        if (!code.equals(stringRedisTemplate.opsForValue().get(phoneNumber))) {
            throw new InvalidGrantException("sms code is wrong or expired");
        }
        RestInfo restInfo = userFeign.getPhoneUser(phoneNumber);
        if (!restInfo.isSuccess()) {
            throw new InvalidGrantException(restInfo.getMessage());
        }
        SysUserAccount user = (SysUserAccount) restInfo.getResult();
        stringRedisTemplate.delete(phoneNumber);
        if (user == null) {
            throw new InvalidGrantException("User account is disabled");
        }
        if ("1".equals(user.getAccountStatus())) {
            throw new InvalidGrantException("User account is locked");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : user.getSysRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(new SecurityUserDetails(user.getAccount(), "N/A", authorities, user.getUserId()), null, authorities);
        usernamePasswordAuthenticationToken.setDetails(authentication.getDetails());
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

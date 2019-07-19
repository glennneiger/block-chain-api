package com.hb56.security.service;

import com.hb56.common.result.RestInfo;
import com.hb56.resource.owner.SecurityUserDetails;
import com.hb56.security.config.LockUserSettings;
import com.hb56.security.dao.SecurityUserFeign;
import com.hb56.security.model.SysRole;
import com.hb56.security.model.SysUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Been
 * @date 2018/12/20
 */
public class CustomUserServiceImpl implements UserDetailsService {
    @Autowired
    private LockUserSettings settings;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SecurityUserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestInfo restInfo = userFeign.getUserAccount(username);
        if (!restInfo.isSuccess()) {
            throw new InvalidGrantException(restInfo.getMessage());
        }
        SysUserAccount user = (SysUserAccount) restInfo.getResult();
        if (user == null) {
            throw new InvalidGrantException("User account is disabled");
        }
        if ("1".equals(user.getAccountStatus()) || stringRedisTemplate.opsForValue().get(username + settings.getKeyLocked()) != null) {
            throw new InvalidGrantException("User account is locked");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : user.getSysRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new SecurityUserDetails(username, user.getPassword(), authorities, user.getUserId());
    }
}

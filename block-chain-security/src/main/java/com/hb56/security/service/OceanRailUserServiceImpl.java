package com.hb56.security.service;

import com.hb56.resource.owner.SecurityUserDetails;
import com.hb56.security.dao.OceanRailUserFeign;
import com.hb56.security.model.oceanrail.Role;
import com.hb56.security.model.oceanrail.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OceanRailUserServiceImpl implements UserDetailsService {
    @Autowired
    private OceanRailUserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeign.get(username);
        if (user == null) {
            throw new InvalidGrantException("用户不存在");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRolesList()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new SecurityUserDetails(username,user.getPassword(), authorities, user.getId());
    }
}

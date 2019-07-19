package com.hb56.block.eureka.zuul.config;

import com.hb56.block.config.config.StripPrefixOfPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Been
 */
@Service
public class RbacService {
    private final StripPrefixOfPath stripPrefix;

    @Autowired
    public RbacService(StripPrefixOfPath stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String url = stripPrefix.getUrl(request.getRequestURI());
        return true;
    }
}

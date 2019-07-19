package com.hb56.block.eureka.zuul.config;

import com.hb56.resource.owner.SecurityUserDetails;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Been
 */
@Component
public class UserFilter extends ZuulFilter {
    private static final String USER_HEADER = "x-user-id";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            SecurityUserDetails details = (SecurityUserDetails) authentication.getPrincipal();
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.addZuulRequestHeader(USER_HEADER, String.valueOf(details.getUserId()));
        }
        return null;
    }
}

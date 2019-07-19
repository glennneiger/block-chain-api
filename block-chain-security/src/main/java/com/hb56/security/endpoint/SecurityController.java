package com.hb56.security.endpoint;


import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Been
 * @date 2019/1/17
 */
@Controller
public class SecurityController {

    @Autowired
    private AuthorizationServerTokenServices tokenServices;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/oauth/logout")
    @ResponseBody
    public RestInfo logout(OAuth2Authentication oAuth2Authentication, HttpServletRequest request) {
        OAuth2AccessToken accessToken = tokenServices.getAccessToken(oAuth2Authentication);
        if (accessToken != null) {
            consumerTokenServices.revokeToken(accessToken.getValue());
        }
        request.getSession().invalidate();
        new SecurityContextLogoutHandler().logout(request, null, null);
        return RestUtil.setSuccessMsg("logout");
    }

    @ApiOperation("")
    @GetMapping("/user")
    @ResponseBody
    public UserDetails getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}

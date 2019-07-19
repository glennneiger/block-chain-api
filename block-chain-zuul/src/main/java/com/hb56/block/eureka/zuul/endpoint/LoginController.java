package com.hb56.block.eureka.zuul.endpoint;

import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Been
 * @date 2019/1/17
 */
@RestController
public class LoginController {

    @GetMapping("/")
    public Principal login(Principal user) {
        return user;
    }

    @GetMapping("/out")
    public RestInfo zz() {
        return RestUtil.setSuccessMsg("已退出!");
    }


}

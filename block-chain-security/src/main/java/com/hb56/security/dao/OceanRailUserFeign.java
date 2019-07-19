package com.hb56.security.dao;

import com.hb56.security.model.oceanrail.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Been
 * @date 2019/4/22
 */
@FeignClient(value = "ocean-rail-security-client")
public interface OceanRailUserFeign {
    @GetMapping("/user/get")
    User get(@RequestParam("loginName") String account);
}

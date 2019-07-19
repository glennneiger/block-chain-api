package com.hb56.security.dao;

import com.hb56.common.result.RestInfo;
import com.hb56.security.fallback.SecurityUserFallback;
import com.hb56.security.model.SysUserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Been
 * @date 2019/4/22
 */
@FeignClient(value = "block-user", fallbackFactory = SecurityUserFallback.class)
public interface SecurityUserFeign {
    @GetMapping("/security/user")
    RestInfo<SysUserAccount> getUserAccount(@RequestParam("account") String account);

    @GetMapping("/security/user/{phone}")
    RestInfo<SysUserAccount> getPhoneUser(@PathVariable("phone") String phone);
}

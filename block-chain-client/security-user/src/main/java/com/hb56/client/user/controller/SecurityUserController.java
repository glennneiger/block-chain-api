package com.hb56.client.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.hb56.client.user.model.SysUserAccount;
import com.hb56.client.user.service.SysUserAccountService;
import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Been
 * @date 2019/5/8
 */
@RestController
public class SecurityUserController {

    @Autowired
    private SysUserAccountService accountService;

    @GetMapping("/security/user")
    public RestInfo<SysUserAccount> getUserAccount(@RequestParam String account) {
        return RestUtil.setData(accountService.getUserAccount(account));
    }

    @GetMapping("/security/user/{phone}")
    public RestInfo<SysUserAccount> getPhoneUser(@PathVariable String phone) {
        return RestUtil.setData(accountService.getPhoneUser(phone));
    }


    @GetMapping("/zz")
    public Object get() throws JsonProcessingException {
        SysUserAccount userAccount = new SysUserAccount();
        userAccount.setAccount("zz");
        String fuser = JSON.toJSONString(userAccount, SerializerFeature.WriteMapNullValue);
        SysUserAccount sysUserAccount = JSON.parseObject(fuser, SysUserAccount.class);
        String guser = new GsonBuilder().serializeNulls().create().toJson(userAccount);
        ObjectMapper mapper = new ObjectMapper();
        String juser = mapper.writeValueAsString(userAccount);
        Object jsus = net.sf.json.JSONObject.fromObject(userAccount);
        return fuser;
    }

}

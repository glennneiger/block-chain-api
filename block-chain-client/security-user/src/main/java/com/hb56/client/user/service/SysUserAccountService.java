package com.hb56.client.user.service;

import com.hb56.client.user.dao.SysUserAccountMapper;
import com.hb56.client.user.model.SysUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Been
 * @date 2019/5/8
 */
@Service
public class SysUserAccountService {
    @Autowired
    private SysUserAccountMapper accountMapper;

    public SysUserAccount getUserAccount(String account) {
        return accountMapper.getUserAccount(account);
    }

    public SysUserAccount getPhoneUser(String phone) {
        return accountMapper.getPhoneUser(phone);
    }
}

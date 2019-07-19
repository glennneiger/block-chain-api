package com.hb56.client.user.dao;


import com.hb56.client.user.model.SysUserAccount;

/**
 * @author Been
 */
public interface SysUserAccountMapper {

    /**
     * login
     *
     * @param account
     * @return
     */
    SysUserAccount getUserAccount(String account);

    SysUserAccount getPhoneUser(String phone);
}
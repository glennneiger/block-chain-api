package com.hb56.client.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Been
 */
public class SysUserAccount implements Serializable {
    private Long sysUserAccountId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 来源平台
     */
    private String sourcePlatform;

    /**
     * 扩展字段
     */
    private String extra;

    /**
     * 资源id
     */
    private String registerSourceId;

    private Long userId;
    private List<SysRole> sysRoles;

    /**
     * 账号状态 0待审核
     */
    private String accountStatus;

    private String phone;

    private static final long serialVersionUID = 1L;

    public Long getSysUserAccountId() {
        return sysUserAccountId;
    }

    public void setSysUserAccountId(Long sysUserAccountId) {
        this.sysUserAccountId = sysUserAccountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform == null ? null : sourcePlatform.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public String getRegisterSourceId() {
        return registerSourceId;
    }

    public void setRegisterSourceId(String registerSourceId) {
        this.registerSourceId = registerSourceId == null ? null : registerSourceId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
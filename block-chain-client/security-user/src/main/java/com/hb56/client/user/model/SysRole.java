package com.hb56.client.user.model;

import java.io.Serializable;

/**
 * @author Been
 */
public class SysRole implements Serializable {
    private Long sysRoleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String remark;

    private String extra;

    private static final long serialVersionUID = 1L;

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}
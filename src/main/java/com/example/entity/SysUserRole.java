package com.example.entity;

import java.util.Date;

public class SysUserRole
{
    /** 用户ID */
    private Long userId;
    
    /** 角色ID */
    private Long roleId;

    private Date createTime;

    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

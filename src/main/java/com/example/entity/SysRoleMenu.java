package com.example.entity;


import javax.xml.crypto.Data;

public class SysRoleMenu
{
    /** 角色ID */
    private Long roleId;
    
    /** 菜单ID */
    private Long menuId;

    private Data creatTime;

    private Data updateTime;

    public Data getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Data creatTime) {
        this.creatTime = creatTime;
    }

    public Data getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Data updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

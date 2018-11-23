package cn.zzpigt.bean;

import java.util.List;

public class SysRoleVo {
    private Long roleId;

    private String roleName;

    private String roleMemo;

    private List<SysUserRoleKeyVo> userList;

    public List<SysUserRoleKeyVo> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUserRoleKeyVo> userList) {
        this.userList = userList;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo == null ? null : roleMemo.trim();
    }
}
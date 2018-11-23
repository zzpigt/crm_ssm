package cn.zzpigt.bean;

public class SysUserRoleKeyVo {
    private SysRole roleId;

    private SysUser userId;

    public SysRole getRoleId() {
        return roleId;
    }

    public void setRoleId(SysRole roleId) {
        this.roleId = roleId;
    }

    public SysUser getUserId() {
        return userId;
    }

    public void setUserId(SysUser userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysUserRoleKeyVo{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
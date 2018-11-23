package cn.zzpigt.bean;

import java.util.Arrays;

public class QueryVo {

    private Integer pageNum;
    private Integer pageSize;
    //选择按钮
    private Boolean select;

    private CstCustomer customer;
    private CstLinkman linkman;
    private SysUser user;
    private SysRole role;
    //checkbox 角色选择
    private Long[] roleId;

    public Long[] getRoleId() {
        return roleId;
    }

    public void setRoleId(Long[] roleId) {
        this.roleId = roleId;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public CstLinkman getLinkman() {
        return linkman;
    }

    public void setLinkman(CstLinkman linkman) {
        this.linkman = linkman;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public CstCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CstCustomer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", select=" + select +
                ", customer=" + customer +
                ", linkman=" + linkman +
                ", user=" + user +
                ", role=" + role +
                ", roleId=" + Arrays.toString(roleId) +
                '}';
    }
}

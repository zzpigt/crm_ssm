package cn.zzpigt.service;

import cn.zzpigt.bean.*;

import java.util.List;

public interface SysUserService {
    SysUser getUserByCodeAndUpwd(SysUser sysUser) throws Exception;

    void registUser(SysUser sysUser) throws Exception;

    PageBean<SysUserVo> getUserList(QueryVo vo);

    SysUserVo getUserVo(Long userId);

    List<SysRole> getRoleList();

    void updateUserAndRole(QueryVo vo) throws Exception;
}

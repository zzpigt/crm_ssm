package cn.zzpigt.service;

import cn.zzpigt.bean.PageBean;
import cn.zzpigt.bean.QueryVo;
import cn.zzpigt.bean.SysRole;

public interface SysRoleService {

    PageBean<SysRole> getRoleList(QueryVo vo);
}

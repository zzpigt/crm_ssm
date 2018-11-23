package cn.zzpigt.service.impl;

import cn.zzpigt.bean.PageBean;
import cn.zzpigt.bean.QueryVo;
import cn.zzpigt.bean.SysRole;
import cn.zzpigt.bean.SysRoleExample;
import cn.zzpigt.mybatis.mapper.SysRoleMapper;
import cn.zzpigt.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public PageBean<SysRole> getRoleList(QueryVo vo) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();

        if (vo != null && vo.getRole() != null && vo.getRole().getRoleName() != null && vo.getRole().getRoleName().length() > 0) {
            criteria.andRoleNameLike("%" + vo.getRole().getRoleName() + "%");
        }
        //查询数据库中客户总数
        int totalCount = roleMapper.countByExample(example);
        PageBean<SysRole> pageBean = new PageBean<>(vo.getPageNum(), vo.getPageSize(), totalCount);

        example.setStart(pageBean.getStart());
        example.setSize(pageBean.getPageSize());

        List<SysRole> list = roleMapper.selectByExample(example);

        pageBean.setList(list);
        return pageBean;

    }
}

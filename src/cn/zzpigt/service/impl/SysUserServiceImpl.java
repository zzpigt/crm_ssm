package cn.zzpigt.service.impl;

import cn.zzpigt.bean.*;
import cn.zzpigt.mybatis.mapper.SysRoleMapper;
import cn.zzpigt.mybatis.mapper.SysUserMapper;
import cn.zzpigt.mybatis.mapper.SysUserRoleMapper;
import cn.zzpigt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sum;
    @Autowired
    private SysUserRoleMapper surm;
    @Autowired
    private SysRoleMapper srm;


    @Override
    public SysUser getUserByCodeAndUpwd(SysUser sysUser) throws Exception{
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
       criteria.andUserCodeEqualTo(sysUser.getUserCode());
        //查询用户名是否存在
        List<SysUser> uList =sum.selectByExample(example);
        if(uList != null && uList.size() > 0){
            //用户名存在,查询用户名和密码匹配的用户是否存在
            criteria.andUserPasswordEqualTo(sysUser.getUserPassword());
            List<SysUser> list =sum.selectByExample(example);
            if(list != null && list.size() > 0){
                //用户名和密码都正确
                return list.get(0);
            }else{
                throw new Exception("密码错误！！");
            }
        }else{
            throw new Exception("用户名不存在！！");
        }
    }

    @Override
    public void registUser(SysUser sysUser) throws Exception {
        System.out.println(sysUser);
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(sysUser.getUserCode());

        //判断用户code是否已经存在
        List<SysUser> user = sum.selectByExample(example);
        if(user != null && user.size() > 0){
            //已经存在这个用户
            throw new Exception("用户名已经存在！！请重新注册");
        }

        //不存在就插入这个用户
        sysUser.setUserState("1");
        sum.insertSelective(sysUser);
    }

    @Override
    public PageBean<SysUserVo> getUserList(QueryVo vo) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();

        if (vo != null && vo.getUser() != null && vo.getUser().getUserName() != null && vo.getUser().getUserName().length() > 0) {
            criteria.andUserNameLike("%" + vo.getUser().getUserName() + "%");
        }
        //查询数据库中客户总数
        int totalCount = sum.countByExample(example);
        PageBean<SysUserVo> pageBean = new PageBean<>(vo.getPageNum(), vo.getPageSize(), totalCount);
        example.setStart(pageBean.getStart());
        example.setSize(pageBean.getPageSize());
        List<SysUser> list = sum.selectByExample(example);
        List<SysUserVo> voList = new ArrayList<>();
        for (SysUser c : list) {
            SysUserVo userVo = new SysUserVo(c);
            //这里是list中单独的一个用户，现在要设置它的vo中List<UserRoleVo>
            //根据userid查关系表sys_user_role，
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            SysUserRoleExample.Criteria sysUserRoleExampleCriteria = sysUserRoleExample.createCriteria();
            sysUserRoleExampleCriteria.andUserIdEqualTo(c.getUserId());
            List<SysUserRoleKey> userRoleListPo = surm.selectByExample(sysUserRoleExample);
            //查到的是这个userid对应的关系表，有了这个表，我们就可以根据其中的rid把所有对应的role对象放到UserRoleVo中
            //关系表的Vo对象就给弄出来了
            List<SysUserRoleKeyVo> userRoleVo = new ArrayList<>();
            for (SysUserRoleKey urk: userRoleListPo) {
                SysUserRoleKeyVo urVo = new SysUserRoleKeyVo();
                urVo.setUserId(c);
                urVo.setRoleId(srm.selectByPrimaryKey(urk.getRoleId()));
                userRoleVo.add(urVo);
            }
            System.out.println("userRoleVo 这个是空么:"+userRoleVo);
            userVo.setRoleList(userRoleVo);
            voList.add(userVo);
            System.out.println("userVo:非常重要 "+userVo);
        }
        System.out.println("volist: "+voList);
        pageBean.setList(voList);
        return pageBean;
    }

    @Override
    public SysUserVo getUserVo(Long userId) {
        //第一步，根据ID查到这个user，放到vo中
        SysUser user = sum.selectByPrimaryKey(userId);
        SysUserVo userVo = new SysUserVo(user);
        //第二步，要设置它的vo中List<UserRoleVo>
        //根据userid查关系表sys_user_role，
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria sysUserRoleExampleCriteria = sysUserRoleExample.createCriteria();
        sysUserRoleExampleCriteria.andUserIdEqualTo(userId);
        List<SysUserRoleKey> userRoleListPo = surm.selectByExample(sysUserRoleExample);
        //查到的是这个userid对应的关系表，有了这个表，我们就可以根据其中的rid把所有对应的role对象放到UserRoleVo中
        //关系表的Vo对象就给弄出来了
        List<SysUserRoleKeyVo> userRoleVo = new ArrayList<>();

        for (SysUserRoleKey urk: userRoleListPo) {
            SysUserRoleKeyVo urVo = new SysUserRoleKeyVo();
            urVo.setUserId(user);
            urVo.setRoleId(srm.selectByPrimaryKey(urk.getRoleId()));
            userRoleVo.add(urVo);
        }
        System.out.println("userRoleVo 这个是空么:"+userRoleVo);
        userVo.setRoleList(userRoleVo);
        System.out.println("userVo:非常重要 "+userVo);
        return userVo;
    }

    @Override
    public List<SysRole> getRoleList() {
        List<SysRole> roles = srm.selectByExample(null);
        return roles;
    }

    @Override
    public void updateUserAndRole(QueryVo vo) throws Exception {
        //第一步，更新用户表
        sum.updateByPrimaryKeySelective(vo.getUser());
        //第二步，更新用户角色关系表(删除这个用户以前的全部关系表中的数据，然后插入新的)
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();

        if(vo != null && vo.getUser() != null && vo.getUser().getUserId() != null){
            criteria.andUserIdEqualTo(vo.getUser().getUserId());
            surm.deleteByExample(example);
        }else{
            throw new Exception("有异常，不能删表");
        }

        for (Long rodeId: vo.getRoleId()) {
            SysUserRoleKey urk = new SysUserRoleKey();
            urk.setRoleId(rodeId);
            urk.setUserId(vo.getUser().getUserId());
            surm.insert(urk);
        }
    }
}

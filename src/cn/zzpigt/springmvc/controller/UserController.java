package cn.zzpigt.springmvc.controller;


import cn.zzpigt.bean.*;
import cn.zzpigt.service.SysUserService;
import cn.zzpigt.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService sus;

    @RequestMapping("/edit.action")
    public String edit(QueryVo vo) throws Exception {
        //调用service层，修改用户表和用户角色关系表
        sus.updateUserAndRole(vo);
        return "redirect:/user/list.action";
    }


    @RequestMapping("/toEdit.action")
    public String toEdit(Long userId, Model model){
        SysUserVo userVo = sus.getUserVo(userId);
        //拿到所有的角色对象
        List<SysRole> roleList = sus.getRoleList();
        model.addAttribute("user", userVo);
        model.addAttribute("roleList", roleList);
        return "/jsp/user/edit.jsp";
    }

    @RequestMapping("/list.action")
    public String list(QueryVo vo, Model model){
        //调用service方法，获取pageBean
        PageBean<SysUserVo> pageBean = sus.getUserList(vo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryVo", vo);

        return "/jsp/user/list.jsp";
    }

    @RequestMapping("/regist.action")
    public String regist(SysUser sysUser, Model model){
        //md5加密
        sysUser.setUserPassword(MD5Utils.md5(sysUser.getUserPassword()));
        System.out.println(sysUser);
        try {
            sus.registUser(sysUser);
            return "/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", e);
            return "/regist.jsp";
        }

    }


    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    public String toLogin(){
        return "/login.jsp";
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(SysUser sysUser, HttpSession session, Model model){
        //md5加密
        sysUser.setUserPassword(MD5Utils.md5(sysUser.getUserPassword()));
        System.out.println(sysUser.getUserCode()+"-----"+sysUser.getUserPassword());
        // 调用service获取这个用户名和密码对应的user对象
        try {
            SysUser	u = sus.getUserByCodeAndUpwd(sysUser);
            // 登录成功
            // session中放入uid
            session.setAttribute("user", u);
            // 转到首页
            return "redirect:/index.jsp";

        } catch (Exception e) {
            // 如果是空就登录失败
            session.removeAttribute("uid");
            // 提示用户失败原因
            model.addAttribute("errorMsg", e.getMessage());
            return "/login.jsp";
        }

    }

}

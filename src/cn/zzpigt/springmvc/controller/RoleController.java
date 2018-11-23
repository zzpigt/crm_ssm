package cn.zzpigt.springmvc.controller;


import cn.zzpigt.bean.PageBean;
import cn.zzpigt.bean.QueryVo;
import cn.zzpigt.bean.SysRole;
import cn.zzpigt.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @RequestMapping("/list.action")
    public String list(QueryVo vo, Model model){
        //调用service方法，获取pageBean
        PageBean<SysRole> pageBean = roleService.getRoleList(vo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryVo", vo);

        return "/jsp/role/list.jsp";
    }

}

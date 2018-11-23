package cn.zzpigt.springmvc.controller;


import cn.zzpigt.bean.*;
import cn.zzpigt.service.SaleVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("saleVisit")
public class SaleVisitController {

    @Autowired
    private SaleVisitService svService;

    @RequestMapping("/toEdit.action")
    public String toEdit(Long visitId, Model model){
        SaleVisitVo saleVisitVo = svService.getSaleVisit(visitId);
        model.addAttribute("saleVisit", saleVisitVo);
        return "/jsp/salevisit/add.jsp";
    }

    @RequestMapping("/list.action")
    public String list(QueryVo vo, Model model){
        //调用service方法，获取pageBean
        PageBean<SaleVisitVo> pageBean = svService.getSaleVisitList(vo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryVo", vo);

        System.out.println("salevisit list:"+pageBean);

        return "/jsp/salevisit/list.jsp";
    }

    @RequestMapping("/add.action")
    public String addOrEdit(SaleVisit saleVisit, HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        saleVisit.setVisitUserId(user.getUserId());

        System.out.println(saleVisit);
        svService.insertOrUpdate(saleVisit);
        return "/saleVisit/list.action";
    }


}

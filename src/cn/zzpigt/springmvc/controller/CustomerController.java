package cn.zzpigt.springmvc.controller;


import cn.zzpigt.bean.*;
import cn.zzpigt.service.CstCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CstCustomerService ccs;

    @RequestMapping("/sourceCount.action")
    public String sourceCount(Model model){
        System.out.println("客户来源上来了！");
        List<CountInfo> list = ccs.getSourceCount();
        model.addAttribute("list", list);
        model.addAttribute("info", "客户来源");
        System.out.println("客户来源："+list);
        return "/jsp/customer/count.jsp";
    }

    @RequestMapping("/industryCount.action")
    public String industryCount(Model model){
        List<CountInfo> list = ccs.getIndustryCount();
        model.addAttribute("list", list);
        model.addAttribute("info", "所属行业");
        return "/jsp/customer/count.jsp";
    }


    @RequestMapping("/edit.action")
    public String edit(CstCustomer customer, Model model){
        //调用service方法，更新customer客户信息
        ccs.updateCustomer(customer);
        return "/customer/toEdit.action?custId="+customer.getCustId();
    }

    @RequestMapping("/toEdit.action")
    public String toEdit(Long custId, Model model){
        //调用service方法，获取customerPo对象返回给edit.jsp
        CstCustomer customer = ccs.getCustomerById(custId);
        model.addAttribute("customer", customer);
        return "/jsp/customer/edit.jsp";
    }

    @RequestMapping("/list.action")
    public String list(QueryVo vo, Model model){
        //调用service方法，获取pageBean
        PageBean<CstCustomerVo> pageBean = ccs.getCustomerList(vo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryVo", vo);

        return "/jsp/customer/list.jsp";
    }

    @RequestMapping("/add.action")
    public String add(CstCustomer customer){
        //调用service方法，添加客户
        ccs.insertCustomer(customer);
        return "/customer/list.action";
    }

}

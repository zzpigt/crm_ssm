package cn.zzpigt.springmvc.controller;


import cn.zzpigt.bean.*;
import cn.zzpigt.service.CstLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("linkman")
public class LinkmanController {

    @Autowired
    private CstLinkmanService lks;

    @RequestMapping("/toEdit.action")
    public String toEdit(Long id, Model model){
        CstLinkmanVo linkmanVo = lks.getLinkman(id);
        model.addAttribute("linkman", linkmanVo);
        return "/jsp/linkman/add.jsp";
    }

    @RequestMapping("/add.action")
    public String addOrEdit(CstLinkman linkman){
        System.out.println(linkman);
        lks.insertOrUpdateLinkman(linkman);
        return "/linkman/list.action";
    }

    @RequestMapping("/list.action")
    public String list(QueryVo vo, Model model){
        //调用service方法，获取pageBean
        PageBean<CstLinkmanVo> pageBean = lks.getLinkmanList(vo);

        model.addAttribute("pageBean", pageBean);
        model.addAttribute("queryVo", vo);


        return "/jsp/linkman/list.jsp";
    }

}

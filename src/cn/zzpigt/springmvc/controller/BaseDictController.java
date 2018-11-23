package cn.zzpigt.springmvc.controller;

import cn.zzpigt.bean.BaseDict;
import cn.zzpigt.service.BaseDictService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("basedict")
public class BaseDictController {

    @Autowired
    private BaseDictService bds;

//	@RequestMapping("/list.action")
//	public void getBaseDictList(String typecode, HttpServletResponse response) throws IOException{
//		System.out.println(typecode);
//		response.setContentType("text/html; charset=utf-8");
//		List<BaseDict> list = bs.getByTypeCode(typecode);
//		String json = new Gson().toJson(list);
//		response.getWriter().write(json);
//	}


    @RequestMapping(value = "/list.action", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getBaseDictList(String typecode) throws IOException {
        System.out.println(typecode);
        List<BaseDict> list = bds.getByTypeCode(typecode);
        String json = new Gson().toJson(list);
        return json;
    }

}

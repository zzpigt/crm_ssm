package cn.zzpigt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws IOException {

        System.out.println(request.getSession().getAttribute("user"));
        System.out.println(obj);
        if(request.getSession().getAttribute("user") != null || obj.toString().contains("login") || obj.toString().contains("Login") || obj.toString().contains("regist")){
            //放行
            System.out.println("放行");
            return true;
        }else{
            System.out.println("重定向到登入页面");
            response.sendRedirect(request.getContextPath()+"/user/login.action");
            return false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

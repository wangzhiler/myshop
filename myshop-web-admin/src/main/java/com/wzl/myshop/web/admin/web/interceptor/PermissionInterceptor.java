package com.wzl.myshop.web.admin.web.interceptor;

import com.wzl.myshop.commons.constant.ConstantUtils;
import com.wzl.myshop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thinkpad on 2018/9/6.
 */
public class PermissionInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //以login结尾的请求
        if (modelAndView!=null && modelAndView.getViewName()!=null && modelAndView.getViewName().endsWith("login")) {
            TbUser user = (TbUser) request.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (user != null) {
                response.sendRedirect("/main");

            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

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
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Object o) throws Exception {
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        //未登录
        if (user == null) {
            httpServletResponse.sendRedirect("/login");
        }

        //放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView.getViewName());

    }
}

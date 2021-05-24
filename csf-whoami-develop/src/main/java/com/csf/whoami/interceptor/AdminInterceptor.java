package com.csf.whoami.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.csf.whoami.database.TbAdmin;
import com.csf.whoami.database.TbAdminRole;

public class AdminInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        HttpSession session = request.getSession();
        TbAdmin admin = (TbAdmin) session.getAttribute("admin");
        TbAdminRole adminRole = (TbAdminRole) session.getAttribute("adminRole");

        if (session == null) {
            response.sendRedirect("/admin/login");
            return false;
        }

        if (admin == null && adminRole == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

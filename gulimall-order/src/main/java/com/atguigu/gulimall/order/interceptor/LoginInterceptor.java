package com.atguigu.gulimall.order.interceptor;

import com.atguigu.common.vo.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/7 23:29
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberRespVo> threadLocal = new ThreadLocal();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        AntPathMatcher matcher = new AntPathMatcher();
        boolean match = matcher.match("/order/order/status/**", requestURI);
        boolean match1 = matcher.match("/payed/notify", requestURI);
        if (match || match1) {
            return true;
        }
        HttpSession session = request.getSession();
        MemberRespVo loginUser = (MemberRespVo) session.getAttribute("loginUser");
        if (loginUser != null) {
            threadLocal.set(loginUser);
            return true;
        } else {
            request.getSession().setAttribute("msg", "请先进行登录！");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}

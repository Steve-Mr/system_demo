package com.example.system_demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterAutoLogin", urlPatterns = "/*")
public class FilterAutoLogin implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("userIdentify") == null){
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies){
                    if (cookie.getName().equals("autoLogin")) {
                        String[] userInfo = cookie.getValue().split("-");
                        httpSession.setAttribute("userID", userInfo[0]);
                        httpSession.setAttribute("userIdentify", userInfo[1]);

                        System.out.println(userInfo[0] + " " + userInfo[1]);
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}

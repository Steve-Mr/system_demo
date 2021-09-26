package com.example.system_demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "FilterLoginChecker", urlPatterns = "/*")
public class FilterLoginChecker implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/login.jsp", "/logout", "/Servlet_login")));

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String path = httpServletRequest.getRequestURI().substring(
                httpServletRequest.getContextPath().length()).replaceAll("[/]+$", "");
        HttpSession httpSession = httpServletRequest.getSession();
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        boolean loggedIn = httpSession != null && httpSession.getAttribute("userIdentify") != null;

        System.out.println(loggedIn);
        System.out.println(allowedPath);

        if (loggedIn || allowedPath){
            chain.doFilter(request, response);
        }
        else{
            ((HttpServletResponse)response).sendRedirect( httpServletRequest.getContextPath()+ "/login.jsp");
        }
    }
}

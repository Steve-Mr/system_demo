package com.example.system_demo.servlet;

import com.example.system_demo.model.model_user;
import com.example.system_demo.repository.repository_user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Servlet_login", value = "/Servlet_login")
public class Servlet_login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userID = Integer.parseInt(request.getParameter("userid"));
        String password = request.getParameter("password");

        System.out.println(userID);
        System.out.println(password);

        model_user user = null;
        try {
            user = repository_user.checkLogin(userID, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String destPage = "/login.jsp";
        String message = null;

        HttpSession httpSession = request.getSession();
        if (user != null){
            httpSession.setAttribute("userID", userID);
            httpSession.setAttribute("userIdentify", user.getUserIdentify());
            destPage="Servlet_service_list";

            Cookie cookie = new Cookie("autoLogin", userID + "-" + user.getUserIdentify());
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge( 60 * 60 * 24 * 7);
            response.addCookie(cookie);

        }else {
            httpSession.setAttribute("userIdentify",null);
            message = "Invalid user ID or password";
        }

        request.setAttribute("message", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request, response);

    }
}

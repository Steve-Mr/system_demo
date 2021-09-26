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

        String destPage = "/index.jsp";
        String message = null;

        try {
            if (repository_user.checkLogin(userID, password)) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userID", userID);
                message = "true";
                destPage = "Servlet_service_list";
            }else {
                message = "Invalid user ID or password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("message", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request, response);

    }
}

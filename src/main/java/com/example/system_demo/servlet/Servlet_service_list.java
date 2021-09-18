package com.example.system_demo.servlet;

import com.example.system_demo.repository.repository_service;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet_service_list", value = "/Servlet_service_list")
public class Servlet_service_list extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Map.Entry<Integer,String>> list_service = repository_service.getServiceList();
        if (list_service.size() != 0){
            request.setAttribute("list_service", list_service);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/service_list.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}

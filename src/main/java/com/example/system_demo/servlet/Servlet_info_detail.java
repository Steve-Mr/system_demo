package com.example.system_demo.servlet;

import com.example.system_demo.repository.repository_score;
import com.example.system_demo.service.service_score;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet_info_detail", value = "/Servlet_info_detail")
public class Servlet_info_detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        System.out.println(request.getParameter("serviceID"));
        repository_score repository_score = new repository_score();

        service_score score = repository_score.getServiceScore(serviceID);
        if (score != null){
            request.setAttribute("msg_score_info", null);
            request.setAttribute("score_info", score);
        }else {
            request.setAttribute("msg_score_info", "尚无评分");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/info_detail.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

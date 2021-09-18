package com.example.system_demo.servlet;

import com.example.system_demo.repository.repository_eval;
import com.example.system_demo.repository.repository_process;
import com.example.system_demo.repository.repository_score;
import com.example.system_demo.repository.repository_service;
import com.example.system_demo.service.service_score;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet_info_detail", value = "/Servlet_info_detail")
public class Servlet_info_detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        System.out.println(request.getParameter("serviceID"));

        service_score score = repository_score.getServiceScore(serviceID);
        if (score != null){
            request.setAttribute("msg_score_info", null);
            request.setAttribute("score_info", score);
        }else {
            request.setAttribute("msg_score_info", "尚无评分");
        }

        try {
            request.setAttribute("score_info_detail", repository_service.getServiceInfo_withScore(serviceID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("process_results_detail", repository_process.getProcessResults(serviceID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("eval_detail_stars", repository_eval.getServiceEvalStars(serviceID));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/info_detail.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}

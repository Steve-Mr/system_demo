package com.example.system_demo.servlet;

import com.example.system_demo.repository.repository_process;
import com.example.system_demo.repository.repository_service;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "Servlet_graph", value = "/Servlet_graph")
public class Servlet_graph extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageType = request.getParameter("imageType");
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        OutputStream outputStream = response.getOutputStream();
        if (Objects.equals(imageType, "INFO")){
            try {
                JFreeChart chart = repository_service.getServiceInfoChart(serviceID);
                ChartUtils.writeChartAsPNG(outputStream, chart, 600, 400);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (imageType.equals("PROCESS")){
            JFreeChart chart = null;
            try {
                chart = repository_process.getProcessChart(serviceID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ChartUtils.writeChartAsPNG(outputStream, chart, 600, 400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

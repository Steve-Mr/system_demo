package com.example.system_demo.servlet;

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

@WebServlet(name = "Servlet_image", value = "/Servlet_image")
public class Servlet_image extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageType = request.getParameter("imageType");
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        if (Objects.equals(imageType, "INFO")){
            OutputStream outputStream = response.getOutputStream();
            try {
                JFreeChart chart = repository_service.getServiceInfoChart(serviceID);
                ChartUtils.writeChartAsPNG(outputStream, chart, 600, 400);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

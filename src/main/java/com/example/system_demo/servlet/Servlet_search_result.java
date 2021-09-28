package com.example.system_demo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.example.system_demo.repository.repository_service.getServiceList;

@WebServlet(name = "Servlet_search_result", value = "/Servlet_search_result")
public class Servlet_search_result extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //request.setCharacterEncoding("UTF-8");
        String keyWord = request.getParameter("keyWord");

        System.out.println(request.getCharacterEncoding());

        System.out.println(keyWord);

        try {
            List<Map.Entry<Integer, String>> list = getServiceList(keyWord);
            if (list.size() != 0){
                request.setAttribute("list_result", list);

                for (Map.Entry<Integer, String> item : list){
                    System.out.println(item.getKey());
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search_results.jsp");
            requestDispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

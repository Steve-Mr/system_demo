package com.example.system_demo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "Servlet_upload", value = "/Servlet_upload")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
public class Servlet_upload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String DIRECTORY_UPLOAD = "excels";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + DIRECTORY_UPLOAD;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            final Collection<Part> parts = request.getParts();
            for (final Part part: parts){
                part.write(uploadPath + File.separator + part.getSubmittedFileName());
                System.out.println(uploadPath + File.separator + part.getSubmittedFileName());
            }

            response.setContentType("text/html");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('上传成功');");
            printWriter.println("</script>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("upload.jsp");
            requestDispatcher.include(request, response);
        }catch (Exception e){
            response.setContentType("text/html");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('上传失败');");
            printWriter.println("</script>");
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("upload.jsp");
            requestDispatcher.include(request, response);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed");
        }

    }
}

<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/9/14
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service List</title>
</head>
<body>
所有服务如下：<br>
<c:forEach items="${list_service}" var="list_service">
     <a href="Servlet_info_detail?serviceID=${list_service.getKey()}">
             ${list_service.getKey()}  ${list_service.getValue()}<br>
     </a>
</c:forEach>
</body>
</html>

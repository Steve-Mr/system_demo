<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/9/28
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<c:forEach items="${list_result}" var="list_result">
    <a href="Servlet_info_detail?serviceID=${list_result.getKey()}"}>
        ${list_result.getKey()} ${list_result.getValue()}<br>
    </a>
</c:forEach>
</body>
</html>

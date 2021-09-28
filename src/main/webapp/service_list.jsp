<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/9/14
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service List</title>
</head>
<body>
<form action="Servlet_search_result" method="post" accept-charset="UTF-8">
    <label for="keyWord"></label>
    <input type="text" id="keyWord" name="keyWord" size="30" />
    <button type="submit">Search</button>
</form>

所有服务如下：<br>
<c:forEach items="${list_service}" var="list_service">
     <a href="Servlet_info_detail?serviceID=${list_service.getKey()}">
             ${list_service.getKey()}  ${list_service.getValue()}<br>
     </a>
</c:forEach>
</body>
</html>

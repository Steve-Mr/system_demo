<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/9/13
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service Info</title>
</head>
<body>
服务详情为：<br>
<c:if test = "${msg_score_info == null}">
    <c item="${score_info}" var="score_info">
        总评分：${score_info.sumAvg}<br>
        服务产品评分：${score_info.sumAvg1}<br>
        服务过程评分：${score_info.sumAvg2}<br>
        客户服务评价：${score_info.sumAvg3}<br>
    </c>
</c:if>

</body>
</html>

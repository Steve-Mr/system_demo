<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/9/13
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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

<br>
<br>

服务产品评分详情：<br>
<c:forEach items="${score_info_detail}" var="score_info_detail">
    ${score_info_detail[1]} ${score_info_detail[2]} <br>
</c:forEach>
<br>
<br>
<img src="Servlet_image?imageType=INFO&serviceID=${service_id}" alt="产品服务信息得分图">
<br>
<br>
服务过程结果详情：<br>

<table>
    <tr>
        <td><div>总订单数</div></td>
        <td><div>完成</div></td>
        <td><div>预约阶段用户取消</div></td>
        <td><div>预约阶段商家取消</div></td>
        <td><div>下单阶段用户取消</div></td>
        <td><div>下单阶段商家取消</div></td>
        <td><div>派单阶段用户取消</div></td>
        <td><div>派单阶段商家取消</div></td>
    </tr>

    <tr>
    <c:forEach items="${process_results_detail}" var="process_results_detail">

            <td><div><c:out value="${process_results_detail}"/></div></td>

    </c:forEach>

    </tr>
</table>

<br>
<br>

服务评价详情：<br>

<c:forEach items="${eval_detail_stars}" var="star">
    <c:out value="${star}" /><br>
</c:forEach>

</body>
</html>

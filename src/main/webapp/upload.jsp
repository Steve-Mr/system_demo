<%--
  Created by IntelliJ IDEA.
  User: a1975
  Date: 2021/10/26
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="Servlet_upload" enctype="multipart/form-data">
    选择文件：
    <input type="file" name="uploadFile" accept=".xls, .xlsx"/>
    <input type="submit" value="upload">
</form>

</body>
</html>

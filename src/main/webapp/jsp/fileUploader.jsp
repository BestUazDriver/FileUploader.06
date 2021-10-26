<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 24.10.2021
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File upload</title>
</head>
<body>
<form action="/fileUploader" method="post" enctype="multipart/form-data">
    <input id="description" name="description" placeholder="Enter description">
    <br>
    <input type="file" name="file">
    <input type="submit" value="Upload"></form>
</body>
</html>

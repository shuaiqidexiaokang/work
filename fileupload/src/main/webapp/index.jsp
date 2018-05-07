<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/7
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/index">index</a><br>
    <form action="/upload" method="post" enctype="multipart/form-data">
        DESC:<input type="text" name="desc"><br>
        选择文件:<input type="file" name="file" width="120px"><br>
        <input type="submit" value="上传"><br>
    </form>
    <a href="/download">download</a><br>
    <img src="/download" alt="">
</body>
</html>

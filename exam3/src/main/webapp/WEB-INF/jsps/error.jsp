<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>执行操作失败</title>
</head>
<body>
<div align="center">
    <h2>执行操作失败！</h2>
    可能原因如下：<br>
    ${message}<br>
    <a href="/uploadFile.do?startId=0">点此回到对比页面</a>
</div>
</body>
</html>

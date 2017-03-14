<%@ page import="com.quanr.Servlet.LoginServlet" %>
<%@ page import="com.quanr.Users.User" %><%--
  Created by IntelliJ IDEA.
  Users: kingsley.zhang
  Date: 2017/3/8
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎你</title>
</head>
<body>
<H1>欢迎你<%=request.getAttribute("nickname")%></H1>
<a href="/logout"><input type="button" value="登出" /></a>
</body>
</html>

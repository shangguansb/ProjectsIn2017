<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
</head>
<body>
<h3>注册</h3>

<form action="register" method="post">
    用户名:    <input type="text" name="username" id="username" v><br>
    密码:      <input type="password" name="password" id="password"><br>
    昵称:      <input type="text" name="nickname" id="nickname"><br>
               <input type="submit" value="注册"><br>
</form>
<a href="/welcome.jsp"><input type="button" value="首页" /></a>
</body>
</html>
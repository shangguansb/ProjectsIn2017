<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div align="center">
    <h2>用户登录</h2>

    <form method="post" action="/login.do">
        <table align="center" style="width: 50%;background-color:cyan">
            <tr>
                <td>用户名称：</td>
                <td>
                    <input name="userName" type="text" required="required">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input name="password" type="password" required="required">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="确定">
                </td>
                <td>
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    <a href="/login.do">跳过登录</a>
</div>

</body>
</html>

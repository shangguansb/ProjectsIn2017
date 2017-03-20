<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<div style="margin-top:32px;text-align: left;">
    <table style="width:50%;border:2px white solid" align="left">
        <tr style="color: #000;">
            <th style="text-align: center">ID</th>
            <th style="text-align: center">用户名</th>
            <th style="text-align: center">性别</th>
            <th style="text-align: center">年龄</th>
            <th style="text-align: center" colspan="3">选项</th>
        </tr>
        <c:forEach items="${userList}" var="row">
            <tr>
                <td align="center">${row.id}</td>
                <td align="center">${row.username}</td>
                <td align="center">
                    <c:if test="${row.gender == 0}">男</c:if>
                    <c:if test="${row.gender == 1}">女</c:if>
                </td>
                <td align="center">${row.age}</td>
                <td align="center"><a href="modifyUser?id=${row.id}">修改</a></td>
                <td><a href="deleteUser?id=${row.id}">删除</a></td>
                <td><a href="detail?id=${row.id}">详情</a></td>
            </tr>

        </c:forEach>
        <tr><td colspan="5"><a href="addUser">添加用户</a></td></tr>
    </table>
</div>
<%--<div style="margin-top:10px;text-align: left;">--%>
<br>

<%--</div>--%>
<%--<div style="margin-top:82px;text-align: center;">--%>
<%--<h3>日期类型转换测试：<a href="/binding_date.do?date=2016-8-10">接口测试</a></h3>--%>
<%--</div>--%>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style type="text/css">
        #myTable {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            width: 50%;
            border-collapse: collapse;
        }

        #myTable td, #myTable th {
            font-size: 1em;
            border: 1px solid aquamarine;
            padding: 3px 7px 2px 7px;
            text-align: center;
        }

        #myTable th {
            font-size: 1.1em;
            text-align: center;
            padding-top: 5px;
            padding-bottom: 4px;
            background-color: #fff;
            color: #000;
        }

        #myTable tr.alt td {
            color: #000000;
            background-color: #fff;
        }
    </style>
</head>
<body>
<div style="margin-top:32px;text-align: left;">
    <h3>  用户名：${singleUser.username}</h3>
    <table id="myTable" align="left">
        <tr>
            <th>名称</th>
            <th>信息</th>
            <th>修改</th>
        </tr>

        <tr>
            <td>ID</td>
            <td>${singleUser.id}</td>
            <td rowspan="7"><a href="modifyUser?id=${singleUser.id}">修改</a></td>
        </tr>

        <tr class="alt">
            <td>姓名</td>
            <td>${singleUser.username}</td>
        </tr>

        <tr>
            <td>性别</td>
            <td>
                <c:if test="${singleUser.gender == 0}">男</c:if>
                <c:if test="${singleUser.gender == 1}">女</c:if>
            </td>
        </tr>

        <tr class="alt">
            <td>年龄</td>
            <td>${singleUser.age}</td>
        </tr>

        <tr>
            <td>备注</td>
            <td>${singleUser.remark}</td>
        </tr>

        <tr class="alt">
            <td>注册时间</td>
            <td>${singleUser.registerTime.toString("yyyy-MM-dd HH:mm:ss")}</td>
        </tr>
        <tr>
            <td>最后修改时间</td>
            <td>${singleUser.lastModifyTime.toString("yyyy-MM-dd HH:mm:ss")}</td>
        </tr>
        <tr><td colspan="5"><a href="list">返回</a></td></tr>
    </table>
</div>

</body>
</html>

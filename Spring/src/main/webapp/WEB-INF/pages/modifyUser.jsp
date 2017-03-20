<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> 修改用户 </title>
    <meta name="Generator" content="EditPlus">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta charset="utf-8">
    <style type="text/css">
        div {
            width: 35%;
            margin-left: 32%;
        }
    </style>
</head>
<body>
<div>
    <form method="post">
        <fieldset>
            <legend>修改用户信息</legend>
            <table width=100%>
                <tbody>
                <tr>
                    <td class=“left” width=40% align="right"><label for="name">姓名：</label></td>
                    <td class="right"><input type="text" id="name" name="username" value=${singleUser.username}></td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="gender">性 别：</label></td>
                    <td class="right">
                        <select name="gender" id="gender">
                            <option value="0"
                            <c:if test="${singleUser.gender == 0}">selected</c:if>>男</option>
                            <option value="1"
                            <c:if test="${singleUser.gender == 1}">selected</c:if>>女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="age">年龄：</label></td>
                    <td class="right"><input type="text" id="age" name="age" value=${singleUser.age}></td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="valid">有效性：</label></td>
                    <td class="right">
                        <select name="isValid" id="valid">
                            <option value="0"
                            <c:if test="${singleUser.isValid == 0}">selected</c:if>>无效</option>
                            <option value="1"
                            <c:if test="${singleUser.isValid == 1}">selected</c:if>>有效</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="txtarea">简 介：</label></td>
                    <td><textarea id="txtarea" name="remark">${singleUser.remark}</textarea></td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right" rowspan=2>
                        <input id="Submit1" type="submit" value="提 交"/>
                    </td>
                    <td><input id="Reset1" type="reset" value="重 置"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </fieldset>
    </form>
</div>

</body>
</html>

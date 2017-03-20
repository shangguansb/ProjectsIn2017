<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 添加用户 </title>

</head>
<body>
<div>
    <form action="/addUser" method="post">
            <legend>添加用户</legend>
            <table style="width:0%;border:2px white solid" align="left">
                <tbody>
                <tr>
                    <td class=“left” width=40% align="right"><label for="name">姓名：</label></td>
                    <td class="right"><input type="text" id="name" name="username"></td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="gender0">性 别：</label></td>
                    <td class="right"><input type="radio" id="gender0" name="gender" value="0" checked/>男
                        <input type="radio" id="gender1" name="gender" value="1"/>女
                    </td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="age">年龄：</label></td>
                    <td class="right"><input type="number" id="age" name="age"></td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="valid0">有效性：</label></td>
                    <td class="right"><input type="radio" id="valid0" name="isValid" value="1" checked/>有效
                        <input type="radio" id="valid1" name="isValid" value="0"/>无效
                    </td>
                </tr>
                <tr>
                    <td class=“left” width=40% align="right"><label for="txtarea">简 介：</label></td>
                    <td><textarea id="txtarea" name="remark"></textarea></td>
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
    </form>
</div>
</body>
</html>

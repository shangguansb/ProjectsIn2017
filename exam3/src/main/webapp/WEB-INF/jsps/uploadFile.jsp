<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<div style="position: relative;left: 10%">
    <c:if test="${userName != null}">
        hello,${userName}! <a href="/logout.do">退出登录</a><br>
    </c:if>
    <c:if test="${userName == null}">
        hello! <a href="/index.do">登录</a><br>
    </c:if>
</div>
<h3 style="position: relative;left: 10%">对比文件</h3>

<div style="position: relative;left: 10%">
    <form action="/processingFile.do" method="post" enctype="multipart/form-data">
        <input type="file" name="source">
        <input type="file" name="target">
        <input type="submit" value="上传文件"><br>
    </form>
</div>

<h3 style="position: relative;left: 10%">最近${sum}条历史对比结果</h3>
<table align="center" border="1" style="width: 80%;background-color: aliceblue">
    <tr style="background-color: darkcyan">
        <th>对比时间</th>
        <th>原文件内容</th>
        <th>目标文件内容</th>
        <th>差异</th>
        <c:if test="${userName != null}">
            <th>操作</th>
        </c:if>
    </tr>
    <c:forEach items="${fileDiffList}" var="fileDiff">
        <tr>
            <td><fmt:formatDate value="${fileDiff.diffTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${fileDiff.sourceFile}</td>
            <td>${fileDiff.targetFile}</td>
            <td>${fileDiff.fileDiff}</td>
            <c:if test="${userName != null}">
                <td>
                    <a href="/deleteFileDiff.do?fileDiffId=${fileDiff.id}">删除历史对比记录</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<div align="center">
    <c:if test="${start > 0}">
        <a href="/uploadFile.do?startId=${start-3}">上一页</a>
    </c:if>
    <c:if test="${start + 3 < sum}">
        <a href="/uploadFile.do?startId=${start+3}">下一页</a>
    </c:if>
</div>
</body>
</html>

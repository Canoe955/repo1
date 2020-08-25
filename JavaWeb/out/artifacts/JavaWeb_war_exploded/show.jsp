<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1 align="center">图书列表</h1>
<table align="center" width="60%" border="1">
    <tr>
        <th>书名</th>
        <th>单价</th>
        <th>分类</th>
    </tr>
    <c:forEach items="${bookList}" var="book">
        <tr>
            <td>${book.bname }</td>
            <td>${book.price }</td>
            <c:choose>
                <c:when test="${book.category eq 1 }"><td style="color: red">JAVASE</td></c:when>
                <c:when test="${book.category eq 2 }"><td style="color: blue">JAVAEE</td></c:when>
                <c:when test="${book.category eq 3 }"><td style="color: green">JAVAFramework</td></c:when>
            </c:choose>
        </tr>

    </c:forEach>
</table>

</body>
</html>

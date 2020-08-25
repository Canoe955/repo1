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
<h1>链接页面</h1>

<a href='<c:url value="/BookServlet?method=findAll" />'>查询所有</a></br>
<a href="<c:url value="/BookServlet?method=findByCategroy&category=1" />">查询SE</a></br>
<a href="<c:url value="/BookServlet?method=findByCategroy&category=2" />">查询EE</a></br>
<a href="<c:url value="/BookServlet?method=findByCategroy&category=3" />">查询Framework</a></br>
</body>
</html>

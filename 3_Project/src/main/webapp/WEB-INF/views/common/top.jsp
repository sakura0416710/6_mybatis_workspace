<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set value="${ pageContext.servletContext.contextPath }" var="contextPath" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상단</title>
<script src="${contextPath }/js/jquery-3.7.1.min.js"></script>
<style>
	h1>a{colot:black; text-decoration:none;}
	h1>a:active {color:orangered;}
</style>
</head>
<body>
<%--page 맨 위에 붙여놓는 상단 --%>
<%--request.getContextPath() : 프로젝트 이름 자동끌어오기 --%>
<%-- ${ PageContext.servletContext.contextPath} : EL버전 --%>
<%--변수에 el버전 저장하고 그거호출. scope를 application으로 잡아서 어디든 사용할 수 있도록 하기 --%>

	<h1 align="center"><a href="${ contextPath}">JSP&amp;Servlet JDBC Project</a></h1>


</body>
</html>
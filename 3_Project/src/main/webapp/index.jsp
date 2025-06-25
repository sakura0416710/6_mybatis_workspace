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
<!-- webapp안에 views폴더가있을 땐 View<=>View끼리 이동가능하다.
화면이동이 간편하지만 보안이 취약하다는 단점이 있다.
WEB-INF(서버가 관리하는 폴더)안으로 view파일을 옮기면 View->controller-> view로 이동해야한다.
보안이 올라가고 화면이동이 불편해진다. -->

	<%@ include file="WEB-INF/views/common/top.jsp" %>
	<jsp:include page="WEB-INF/views/common/loginbar.jsp"/>
	
	<!-- 관리자 계정으로 재요청하기 -->
	<c:if test="${ !empty loginUser && loginUser.isAdmin == 'Y'}">
		<c:redirect url="admin.me"/> 
	</c:if>
</body>
</html>
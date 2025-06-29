<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#loginbar{text-align:right; margin: 50px;}
	#loginbar{color:black; text-decoration:none;}
	#loginbar{font-weight:bold; text-decoration:underline;}
</style>
</head>
<body>
	
	<!-- 로그인이 성공했을 때 환영합니다 보이게 하고 아닐 땐 로그인만 보이게. 
	 즉, session에 로그인 데이터가 있는지 비교하면된다. -->
	<div id="loginbar">
		<c:if test="${empty loginUser }">
			<a href="${contextPath }/loginView.user">로그인</a>
		</c:if>
		<c:if test="${not empty loginUser }">
			<b>${loginUser.name }님, 반갑습니다.</b><br/><br/>
			<a href="${contextPath }/editPage.me">내 정보 수정</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${contextPath }/logout.me">로그아웃</a>
			<br/><br/>
			<a href="${contextPath }/list.bo">>>>게시판</a>
		</c:if>
	</div>
	
	
	
	
	
	
	
	
</body>
</html>
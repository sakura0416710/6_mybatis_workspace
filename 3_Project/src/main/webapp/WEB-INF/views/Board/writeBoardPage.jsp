<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#writeDiv{border: 1px solid black; margin: auto; margin-top: 100px; padding: 130px; width: 500px;}
	input[name=title]{height: 30px;}
	button{border: 1px solid black; width: 300px; padding: 15px; border-radius: 15px; background: white;}
	button:hover{background: black; color: white; cursor: pointer;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>
	<div align="center" id="writeDiv">
		<form action="${ contextPath }/insertBoard.bo" method="post">
			<table>
				<tr>
					<c:if test="${ loginUser.isAdmin != 'Y' }">
						<td colspan="2"><input type="text" name="title" placeholder="제목" size="65"></td>
					</c:if>
					<c:if test="${ loginUser.isAdmin == 'Y' }">
						<td><input type="text" name="title" placeholder="제목" size="55"></td>
						<td><input type="checkbox" name="isNotice" id="isNotice" value="Y"> <label for="isNotice">공지</label></td>
					</c:if>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" cols="67" rows="10" style="resize: none;" placeholder="내용을 입력해주세요"></textarea>
					</td>
				</tr>
			</table>
			<br><br>
			<button>작성하기</button>
		</form>
	</div>
</body>
</html>
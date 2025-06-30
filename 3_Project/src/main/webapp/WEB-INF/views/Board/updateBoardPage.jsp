<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#updateDiv{border: 1px solid black; margin: auto; margin-top: 100px; padding: 130px; width: 500px;}
	select, input[name=title]{height: 30px;}
	button{border: 1px solid black; width: 200px; padding: 15px; border-radius: 15px; background: white;}
	button:hover{background: black; color: white; cursor: pointer;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>
	<div align="center" id="updateDiv">
		<form action="${ contextPath }/updateBoard.bo" method="post">
			<input type="hidden" name="bId" value="${ b.boardNo }">
			<table>
				<tr>
					<c:if test="${ loginUser.isAdmin != 'Y' }">
						<td colspan="2"><input type="text" name="title" placeholder="제목" size="65" value="${ b.title }"></td>
					</c:if>
					<c:if test="${ loginUser.isAdmin == 'Y' }">
						<td><input type="text" name="title" placeholder="제목" size="55" value="${ b.title }"></td>
						<td><input type="checkbox" name="isNotice" id="isNotice" value="Y" <c:if test="${ b.isNotice == 'Y' }">checked</c:if>> <label for="isNotice">공지</label></td>
					</c:if>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" cols="67" rows="10" style="resize: none;" placeholder="내용을 입력해주세요">${ b.content }</textarea>
					</td>
				</tr>
			</table>
			<br><br>
			<button>수정하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="location.href='javascript:history.go(-1);'">되돌아가기</button>
		</form>
	</div>
</body>
</html>
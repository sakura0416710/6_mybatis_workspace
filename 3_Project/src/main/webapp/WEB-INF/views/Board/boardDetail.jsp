<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#detailDiv{border: 1px solid black; margin: auto; margin-top: 100px; padding: 130px; width: 500px;}
	select, input[name=title]{height: 30px;}
	button{border: 1px solid black; width: 120px; padding: 15px; border-radius: 15px; background: white;}
	button:hover{background: black; color: white; cursor: pointer;}
	
	#replyDiv{border: 1px solid black; margin: auto; margin-top: 10px; padding: 13px; width: auto;}
 	#submitReply{border: 1px solid black; width: 60px; border-radius: 15px; background: white; height: 50px;} 
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>
	<div align="center" id="detailDiv">
		<form action="${ contextPath }/updateBoardPage.bo" method="post" id="detailForm">
			<table>
				<tr>
					<td colspan="6"><b>${b.isNotice == 'N' ? '' : '[공지]'}</b><br><br></td>
				</tr>
				<tr style="text-align: center;">
					<th width="25px" style="border-right: 1px solid black;">제목</th>
					<td width="80px" style="border-right: 1px solid black;">${b.title }</td>
					<th width="25px" style="border-right: 1px solid black;">작성일</th>
					<td width="50px" style="border-right: 1px solid black;">${b.createDate }</td>
					<th width="25px" style="border-right: 1px solid black;">조회수</th>
					<td width="20px">${ b.count }</td>
				</tr>
				<tr>
					<td colspan="6">
						<input type="hidden" name="bId" value="${ b.boardNo }">
						<textarea name="content" cols="67" rows="10" style="resize: none;" readonly>${b.content }</textarea>
					</td>
				</tr>
			</table>
			<br><br>
			<c:if test="${loginUser.empNo == b.empNo }">
				<button>수정하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="deleteButton">삭제하기</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<button type="button" id="listButton">목록으로 가기</button>
		</form>
		
		<br>
		<br>
		
		<div id="replyDiv">
			<table>
				<tr>
					<td colspan="2">
						<textarea rows="3" cols="50" id="replyContent" style="resize: none;" <c:if test="${ empty loginUser }">readonly placeholder='로그인 후 이용하세요'</c:if>></textarea>
					</td>
					<td><button id="submitReply" <c:if test="${ empty loginUser }">disabled</c:if>>등록</button></td>
				</tr>
				<tbody id="replyList">
					<c:if test="${empty list }">
						<tr>
							<td colspan="3" align="center">
								<br>등록된 댓글이 없습니다.
								<br>이 게시글의 첫 댓글이 되어주세요!
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="r">
							<tr>
								<td width="80px">${r.writer }</td>
								<td>${r.content }</td>
								<td>${r.createDate }</td>
							</tr>
						</c:forEach>
					 </c:if>
				</tbody>
			</table>
		</div>
	</div>
	
	<script>
		window.onload = ()=>{
			document.getElementById('listButton').addEventListener('click', ()=>{
				location.href='${contextPath}/list.bo';
			});
			
			//게시글 삭제 (url에 게시글번호 안보이도록 로케이션 인코딩하기)
			const deleteBtn = document.getElementById('deleteButton');//내 글이 아니면 삭제버튼이 안보이므로
			if(deleteBtn != null){  //!= null =>내 글이라서 삭제버튼이 보인단 뜻
				deleteBtn.addEventListener('click',() =>{
					if(confirm('정말 삭제하시겠습니까?')){
						location.href="${contextPath}/deleteBoard.bo?bId=" + btoa(${b.boardNo});
					}
				});
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	</script>
</body>
</html>
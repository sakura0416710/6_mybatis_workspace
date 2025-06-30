<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#boardDiv{margin-left: 150px; margin-right: 150px; margin-top: 150px; min-height:350px; border: 1px solid black; padding: 30px;}
	#boardDiv>table{width: 100%; text-align: center; height: 100%;}
	th{border-bottom: 1px solid black;}
	#writeLabel{float: right;}
	#writeLabel:hover{font-weight: bold; text-decoration: underline;}
	
	.pagingArea *{margin: 0 10px;}
	.pagingArea a{color: black; text-decoration: none;}
	.pagingArea a:hover{font-weight: bold; text-decoration: underline;}
	.disable{color: lightgray;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>
	<div id="boardDiv">
		<label id="writeLabel">[ 글쓰기 ]</label>
		<br><br><br>
		<table>
			<tr>
				<th width="5%"></th>
				<th width="10%">글번호</th>
				<th width="40%">글제목</th>
				<th width="18%">작성자</th>
				<th width="17%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
			<c:if test="${empty list }">
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty list }">
				<c:forEach>
					<tr>
						<td>${b.isNotice == 'N' ? '' : '[공지]' }</td>
						<td>${b.boardNo }</td>
						<td>${b.title }</td>
						<td>${b.writer }</td>
						<td>${b.createDate }</td>
						<td>${b.count }</td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
		<!-- 페이지 버튼 만들기 -->
		<br/><br/><br/>
		<div class="pagingArea" align="center">
		
			<!-- 이전 --> 
			<c:if test="${pi.currentPage <=1 }">
				<span class="disable">[이전]></span>			
			</c:if>
			<c:url value="${loc }" var="blistBack">		<!-- 현재 url : (contextPath/url)기본적으로 list.bo를 가지고 있게 만들기. c:url에서만 사용가능 -->
				<c:param name="page" value="${pi.currentPage-1 }"/>  <!-- 쿼리스트링 만들기 -->
			</c:url>
			<a href="blistBack">[이전]</a>
			
			<!-- 숫자 -->
			<c:forEach begin="${pi.startPage }" end="${pi.endPage } " var="p">
				<c:if test="${p eq pi.currentPage }">
					<font color="red"><b>[${p}]</b></font>
				</c:if>
				<c:if test="${p ne pi.currentPage }">
					<c:url value="${loc }" var="blistCheck">
						<c:param name="page" value="${p }"/>
					</c:url>
					<a href="${blistCheck }">${ p }</a>
				</c:if>
			</c:forEach>
			
			<!-- 다음 -->
			<c:if test="${pi.currentPage >= pi.maxPage }">
				<span class="disable">[다음]></span>			
			</c:if>
			<c:if test="${pi.currentPage < pi.maxPage}">
				<c:url value="${loc }" var="blistNext">		 -->
					<c:param name="page" value="${pi.currentPage+1}"/> 
				</c:url>
				<a href="${blistNext }">[다음]</a>
			</c:if>
		</div>
	</div>
	<script>
		window.onload = () => {
			document.getElementById('writeLabel').addEventListender('click', () => {
				if('${loginUser}' == ''){
					alert('로그인 후 이용해주세요')
				} else { 
					location.href="${contextPath}/writeBoardPage.bo";
				}
			});
			
			//게시글 클릭 -> 상세페이지 이동
			const tds = document.querySelectorAll('td');'
				//td에 직접 onclick이벤트 걸지말고 for문
			for(const td of tds){
				const parent = td.parentElement;
				td.addEventListener('mouseenter',() => {
					parent.style.backgroundColor = 'darkgray';
					parent.style.cursor = 'pointer';
				});
				td.addEventListener('mouseout' () => {
					parent.style.backgroundColor = 'white';
				});
				td.addEventListener('click' () => { //글번호로 조회하기(유일성) (1번은 공지니까 그 다음부터 글이 시작됨)
					location.href = '${contextPath}/selectBoard.bo?bId='+ parent.children[1].innerText;
				});
				
			}
			
			
			
			
		}
	
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
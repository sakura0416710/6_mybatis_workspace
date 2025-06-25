<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#loginDiv{border: 1px solid black; margin: auto; margin-top: 150px; padding: 150px; width: 500px;}
	#loginDiv>label:hover{font-weight: bold; text-decoration: underline; cursor: pointer;}
	input{height: 30px;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<form action="${ contextPath }/login.user" method="post" id="loginForm">
		<div align="center" id="loginDiv">
			<table>
				<tr>
					<td align="right"><label for="id">사원번호</label></td>
					<td><input type="text" name="id" id="id" size="25" autofocus></td>
				</tr>
				<tr>
					<td align="right"><label for="pwd">비밀번호</label></td>
					<td><input type="password" name="pwd" id="pwd" size="25"></td>
				</tr>
			</table>
			<br>
			<label onclick="doLoginLabel();">로그인</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<label id="findIdPwdLabel">ID/PW 찾기</label>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</form>
	<script>
	//1.로그인 (아이디, 비밀번호가 빈칸이면 경고창 띄우고 정보 둘다 입력 시 로그인 페이지로 이동하기)
	// * form태그(정보를 한꺼번에 묶어서 전송하는) submit()이 포인트였다.
	const doLoginLabel = () => {
			const id = document.getElementById('id');
			const pwd = document.getElementById('pwd');
		
			if(id.value.trim() == ""){
				alert('아이디를 입력하세요.');
				id.focus();
			} else if(pwd.value.trim() == ""){
				alert('비밀번호를 입력하세요');
				pwd.focus();
			} else {
				document.getElementById('loginForm').submit();
			}
		}
// cf. HTML 폼태그에서 input type="submit" (폼태그안에 제출 기능을 하는 버튼이 있다면 엔터눌렀을 때 데이터가 전송됨/
		
		//2. input tag에 엔터눌럿을 때 로그인이 되도록 하기.
		//페이지가 업로드 되면 태그네임에 접근하고 아디와 비번 컬렉션을 담음
		//addEventListenr : 표준 이벤트 모델
		//키보드 이벤트 keyUp에 대한 event객체에 접근했을 때 실행되는 함수작성
	
		
		window.onload = () => {
			const inputIdPw = document.getElementsByTagName('input');
			for(let i = 0; i< inputIdPw.length; i++){
				inputIdPw[i].addEventListener('keyup',e => {
					
					if(e.key =='Enter'){
						doLoginLabel(); //id, pw검사해야하니까 위에 함수 실행시켜서 조건 충족시키기 & Enter눌렀을 때 전송시키기
					}
				});
			}
			
		}
		
	</script>
	
	
	
</body>
</html>
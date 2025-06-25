<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자계정으로 로그인한 경우</title>
<style>
	html, body{width: 99%; height: 98%;}
	#container{width: 99%; height: 75%; display: flex;}
 	#adminDiv{height: 100%; width: 15%; background: #111; display: inline-block;}
	.adminMenu{
		width: 95%; height: 60px; background: lightpink; text-align: center; margin: auto; 
		line-height: 60px; font-size: 20px; color: #eee;
	}
	.adminMenu:hover{font-weight: bold; cursor: pointer;}
	
	.menuContent{margin-left: 10px; width: 85%; height: 100%; display:none;}
	
	#enrollDiv{margin: auto; padding: 130px;}
	#enrollDiv td{text-align: right;} 
	.enrollInput{height: 30px; width: 200px;}
	.enrollInput.select{width: 208px;}
	#enrollButton{border: 1px solid black; width: 300px; padding: 15px; border-radius: 15px; background: white;}
	#enrollButton:hover{background: black; color: white; cursor: pointer;}

	#adminDiv+div{height: 92%; width: 80%; padding: 30px; overflow: scroll;}
	#adminDiv+div>table{width: 100%; text-align: center;}
	th{border-bottom: 1px solid black;}
	#adminDiv+div>table td{height: 35px;}
	
	#adminDiv+div>table td>div{
		border : 1px solid black; height:80%; width:45%; display:inline-block;
		padding-top:3%; cursor:pointer;
		}
		.selectState{background:lightpink;}
		.unselectState{background: none;}
</style>
</head>
<body>
	<jsp:include page="../common/top.jsp"/>
	<jsp:include page="../common/loginbar.jsp"/>

	<hr>
	
	<div id="container">
		<div id="adminDiv">
			<h2 style="margin-left: 5px;">
				<a href="${ contextPath }" style="color: white;">
					JSP&amp;Servlet<br>
					JDBC Project
				</a>
			</h2>
			<div class="adminMenu">사원 전체 보기</div>
			<br>
			<div class="adminMenu">사원 등록 하기</div>
		</div>
		
		<div class="menuContent">
			<table id="empList">
				<tr>
					<th width="6%">사번</th>
					<th width="10%">이름</th>
					<th width="10%">직업</th>
					<th width="10%">매니저</th>
					<th width="10%">입사일</th>
					<th width="10%">급여</th>
					<th width="10%">커미션</th>
					<th width="10%">부서</th>
					<th width="12%">관리자</th>
					<th width="12%">활동여부</th>
				</tr>
			
			<!--사원이 없는데 찾는 경우 대비 -->
				<c:if test="${empty empList}">
					<tr>
						<td colspan="10">조회된 리스트가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${!empty empList }">
					<c:forEach items="${empList }" var="e"> 
					<!-- e에 employee객체가 들어가있을것임.따라서 Employee에 잇는기본 
					getter의 생성자를가져옴컬럼이나 result Set이름이 아니라  -->
					<tr>
					<td>${e.empNo}</td>
					<td>${e.name }</td>
					<td>${e.job}</td>
					<td>${empty e.mgr? '-' : e.mgr }</td>
					<td>${e.hireDate }</td>
					<td>${e.sal}</td>
					<td>${e.comm}</td>
					<td>${e.dept }</td>
					<td>
						<div class="${e.isAdmin=='Y'? 'selectState':'unselectState' }">Y</div>
						<div class="${e.isAdmin=='N'? 'selectState':'unselectState' }">N</div>
					</td>
					<td>
						<div class="${e.status=='Y'? 'selectState':'unselectState' }">Y</div>
			 			<div class="${e.status=='N'? 'selectState':'unselectState' }">N</div>
					</td>
					
					</tr>
					
					</c:forEach>
				</c:if>
			</table>
			
		
		</div>
		<!-- 부서를 필수선택하도록 하기 (안고르면 알럿) --> 
		<div class="menuContent">
			<form action="${ contextPath }/insertEmp.me" method="post" id="enrollForm" name="enrollFrm">
				<div align="center" id="enrollDiv">
					<table>
						<tr>
							<td><label for="id">사원번호</label></td>
							<td><input type="text" name="id" id="id" class="enrollInput" required
								value="${e.getEmpNo}" autofocus></td>
						</tr>
						<tr>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td><label for="name">이름</label></td>
							<td><input type="text" name="name" id="name" class="enrollInput" size="25" required></td>
						</tr>
						<tr>
							<td><label for="job">직업</label></td>
							<td><input type="text" name="job" id="job" class="enrollInput" size="25" required></td>
						</tr>
						<tr>
							<td><label for="mgr">매니저</label></td>
							<td>
								<select name="mgr" class="enrollInput select">
									<option>---------------------------------------</option>
									<!-- 매니저에 대한 선택부분 : forEach로 자동추가되도록 함(아님 넘 길어지니까):아까 썻던 empList를 가져옴 -->
									<c:forEach items="${empList }" var="e">
										<option value="${e.empNo}">${e.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><label for="sal">급여</label></td>
							<td><input type="number" name="sal" class="enrollInput" id="sal" size="25" value="0"></td>
						</tr>
						<tr>
							<td><label for="comm">커미션</label></td>
							<td><input type="number" name="comm" class="enrollInput" id="comm" size="25" value="0"></td>
						</tr>
						<tr>
							<td><label for="dept">부서</label></td>
							<td>
								<select name="dept" class="enrollInput select">
									<option>---------------------------------------</option>
									<!-- DB에 사원등록을 하기 위해-dept랑 Join했을 때 deptNo, dept로 이어줬기 때문  -->
									<option value="10">ACCOUNTING</option>
									<option value="20">RESEARCH</option>
									<option value="30">SALES</option>
									<option value="40">OPERATIONS</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><label for="isAdmin">관리자 여부</label></td>
							<td>
								<input type="checkbox" name='isAdmin' id="isAdmin" value="Y">
							</td>
						</tr>
					</table>
					<br>
					<button id="enrollButton">사원등록</button>
				</div>
			</form>			
		
		
		</div>
	</div>
	<script>
	window.onload =()=>{
		const adminMenus = document.getElementsByClassName('adminMenu');
		const menuContents = document.getElementsByClassName('menuContent');
		
		let validate = false; //유효성 검사
		
		adminMenus[0].addEventListener('click',()=>{
			menuContents[0].style.display='block';
			menuContents[1].style.display='none';
		});
		adminMenus[1].addEventListener('click',() => {
			menuContents[0].style.display='none';
			menuContents[1].style.display='block';
		});
		
		
		//부서 선택 안하고 등록 누르면 필수라는 알럿창을 띄우기 위해서 js로 이동, button과 dept에 접근
		document.getElementById('enrollButton').onclick =(e)=>{
			const dept = document.getElementsByName('dept')[0] //elements는 다 찾아서 배열로 반환을 함
			if(dept.value.indexOf('-')== 0){    //선택을 안 할 경우에 0. 있으면 1 반환함
				alert('부서는 필수 선택항목입니다.'); //form태그 안의 버튼=전송을 눌렀음에도 알럿창만 뜨고 데이터 안 넘어가게 하기(onSubmit=false, onClick=false)말고
				e.preventDefault(); //이벤트 객체 안의 데이터 전송을 막는 이벤트 실행하기.
			
			}
			//등록버튼 눌렀을 때 작동시키기 위해 여기다 써줌
			if(!validate){ //true일때
				alert('사원번호를 확인하세요');
				document.getElementById('id').focus();
				e.preventDefault();
			}
		}
		//afterEnroll=Y가져오기 <-파라미터에 저장된 것이므로 param. 으로 찾아주기
		const afterEnroll = "${param.afterEnroll}"; // =Y에 에러 뜸. Y라는 변수를 나타낸 거나 다름없기때문이다. (우리가 원하는 건 'Y')
		if(afterEnroll == 'Y'){
			menuContents[0].style.display='block'; //이러면 사원추가등록했을 때 사원전체가 보이게 됨
			menuContents[1].style.display='none'; 
		}
		
		//사원번호 중복인거 알림창 띄우기 > 비교할 사원번호를 가지고 있어야 비교가능.
		// 1. id -> value에 접근
		//window객체를 가져오면 function으로 바꿔주기
		/*const id = document.getElementById('id').addEventListener('focusout',function(){
			const value = this.value ;
			});
		*/
		//ajax로 객체{(key:value)}, 통신이기 때문에 url은 필수이다.
		const id = document.getElementById('id').addEventListener('focusout',e => {
			const value = e.target.value.trim();
			//<tr><td>colspan="2"에 접근: 화살표함수로 되어있으므로 target써주고
			const targetTd = e.target.parentElement.parentElement.nextElementSibling.children[0];
	
			
			if(value != ''){
				$.ajax({
					//통신 ; 경로 필수구현 
					url:'${contextPath}/checkEmpNo.me',
					//데이터 전송
					data : {value:value},
					//응답을 받아온 게 성공, 에러로 나눠주고 ()에 받아온 인자 써주기
					succeess : (data) => {
						console.log(data);
						if(data == 0){
							targetTd.innerText = '사용가능한 사원번호입니다.'
							targetTd.style.color = 'green';
							validate = true;
						} else {
							targetTd.innerText = '중복된 사원번호입니다.';
							targetTd.style.color = 'red';
							validate = false;
						}
						targetTd.style.fontSize = '12px';
					},
					error: (data) => {
						console.log(data);
						
					}
				});
				
				//값이 없을 때는 안내문구 빈칸
			} else {
				targetTd.innerText='';
			}
		});
		//table에 있는 div(관리자여부, 활동중) 토글버튼 만들어서 상태 변경하기
		//$(table).find('div'); find()는 제이쿼리 함수라 제이쿼리 형태로 바꾸고! 함수를 써야함
		const table = document.getElementById('empList'); 
		const stateButtons = table.querySelectorAll('div'); //jQuery의 find()와 비슷한 js함수
		for( const button of stateButtons){ 
			//map도 사용가능 (for of = map 인덱스만 검사. 내용물X)
			//nodList는 배열이 아니므로 [...nodeList])처럼 배열로 만들어서 for of, map을돌림)
			button.addEventListener('click', function() {
				//인자에 event객체 넣던가 function으로 하던가
				//function > this(window)사용가능
				//empNo, 내가 선택한 컬럼명(admin/status),값(Y/N)을 전송해야함
				//update emp set empno = ~..
				if(this.className == 'unselectState'){
					//1. empNo가져오기 : div에서 나가서 > td >
					const myTd = this.parentElement;
					//tr >그 첫번째 td
					const myTr = myTd.parentElement;
					const myTrChildren = myTr.children //td들의 배열이 들어가있음 
					const empNo = myTr.children[0].innerText; //그 중에서 0번째필요
					
					//2. 내가 선택한 컬럼명(isAdmin/status)가져오기(for in : 요소출력 O 인덱스 출력은 for of)
					let clickColumnIndex;
					for(const index in myTrChildren){
						if(myTrChildren[index] == myTd){ //Y,N부분인 index랑 tr밑의 td들의 인덱스랑 같으면 해당하는 인덱스의 숫자가 나옴(8 아님 9번째td니까 둘중 하나가 나옴)
							clickColumnIndex = index; //내가 클릭한 index값을 이 변수에 대입
						}
				}
					
					$.ajax({ //this : event가 들어간 button을가리키는 중
						url: '${contextPath}/updateState.me',				//컬럼명을썼기 때문에 (문자열로 값전달) 컬럼명이랑 맞게 써주기
						data:{empNo:empNo, column:clickColumnIndex == 8? 'is_Admin':'status',value:this.innerText},
						type: 'post',
						success: data => {
							if(data.trim() == 'success'){ //success + 줄바꿈들어갔으니까 trim해주기
								this.className = 'selectState';	//이 this = div 
								for(const siblings of myTd.children){
									if(siblings != this){
										siblings.className = 'unselectState';
									}
								}//모든div들 중 여러 td들 중 내가 고른거만 색 빼야함
							} else {
								alert('상태 변경을 실패하심');
							}
						},
						error:data=>console.log(data)
					
					});
			}
		});
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
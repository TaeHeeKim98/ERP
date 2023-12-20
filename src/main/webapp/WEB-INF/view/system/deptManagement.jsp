<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title> 부서관리  </title>

<jsp:include page="/WEB-INF/view/common/common_include.jsp"></jsp:include>

<script type="text/javascript">
		
		
		// 페이징 설정 
		var pageSize = 5;    	// 화면에 뿌릴 데이터 수 
		var pageBlock = 5;		// 블럭으로 잡히는 페이징처리 수
		
		/* onload 이벤트  */
		$(function(){
			// 리스트 뿌리기 함수 
			deptManagementList();
			
			// 버튼 이벤트 등록 (저장, 수정, 삭제, 모달창 닫기)
			fButtonClickEvent();
			
		});

		function fButtonClickEvent(){
			$('a[name=btn]').click(function(e){
				e.preventDefault();   // ?? 
						
				var btnId = $(this).attr('id');			
				//alert("btnId : " + btnId);
				
				switch(btnId){
					case 'searchBtn' : deptManagementList(); 
						break;						
					
						//신규등록
					case 'btnSaveDept' :
						fSaveDept();
						break;
						
						//수정
					case 'btnUpdateDept' :
						fSaveDept();
						break;	
						
						//삭제
					case 'btnDeleteDept' :
						fDeleteDept();
						break;	
						
					case 'btnCloseDept' :  //닫기
						gfCloseModal();
						break;	
				}										
			});
		}
		
		//리스트 출력 + 검색
		function deptManagementList(currentPage) {

		currentPage = currentPage || 1;
		
		var deptname = $('#deptname').val();				
		console.log("deptname : " + deptname);
				
		var param = {
			deptname : deptname,						
			currentPage : currentPage,
			pageSize : pageSize
		}
		var resultCallBack = function(data){
			deptMgmtListResult(data, currentPage);
		}
		callAjax("/system/deptManagementList.do", "post", "text", true, param, resultCallBack );
	} 
	
		
		//페이징
		 function deptMgmtListResult(data, currentPage) {
		    	console.log(data);
		    	
		    	$("#deptManagementList").empty().append(data);
		    	
		    	var totalCnt = $("#totalCnt").val();
		    	
			    var pagingnavi = getPaginationHtml(currentPage, totalCnt, pageSize, pageBlock, 'deptManagementList');
				 
			    console.log("pagingnavi : " + pagingnavi);
				
				$("#pagingnavi").empty().append(pagingnavi);
				$("#currentPage").val(currentPage);
		    	
		    }		
		
		 /** 부서코드 폼 초기화 */
			function fInitFormDept(object) {
				//var dept_cd = parseInt(dept_cd);
				if( object == "" || object == null || object == undefined) {//신규
					
					$("#dept_cd").val("");
					$("#dept_name").val("");
					
			   		$("#btnUpdateDept").hide();				
					$("#btnDeleteDept").hide();
					$("#action").val("I");
				} else {
					
					$("#dept_cd").val(object.dept_cd);
					$("#dept_name").val(object.dept_name);
				
					if(object.dept_cd>0){
						  $("#btnSaveDept").hide();
					}else{
						$("#btnUpdateDept").show();
					    $("#btnDeleteDept").show();
					}

				}
			}
		 
			/** 부서코드 단건 조회 */	
		 function fSelectDept(dept_cd){
				var param = {
						dept_cd : dept_cd  
				   };
				
				var resultCallback = function(data) {
					fSelectDeptResult(data);
				};
				
				callAjax("/system/selectDept.do", "post", "json", true, param, resultCallback);
			}
			
			
		 /** 부서코드 단건 조회 콜백 함수*/
			function fSelectDeptResult(data) {

				if (data.result == "SUCCESS") {

					// 모달 팝업
					gfModalPop("#layer1");
					
					// 그룹코드 폼 데이터 설정
					fInitFormDept(data.DeptMgmtModel);
					
				} else {
					swal(data.resultMsg);
				}	
			}
		 
		 /** 신규등록 모달 실행 */
			function fModalDept(dept_cd) {
				
				// 신규 저장
				if (dept_cd == null || dept_cd=="") {
					$("#action").val("I");
						
					fInitFormDept();
					// 모달 팝업
					gfModalPop("#layer1");

				// 수정 저장
				} else {					
					// 그룹코드 단건 조회
					$("#action").val("U");
			
					fSelectDept(dept_cd);
				}
			}
		 		
			/** 코드 저장 validation */
			function fValidateDept() {

				var chk = checkNotEmpty(
						[
								[ "dept_cd", "부서코드를 입력해 주세요." ]
							,	[ "dept_name", "부서명을 입력해 주세요" ]
							
						]
				);

				if (!chk) {
					return;
				}

				return true;
			}
			
			/** 부서코드 저장 */
			function fSaveDept() {

				
				// vaildation 체크
				if ( ! fValidateDept() ) {
					return;
				}
				
				var resultCallback = function(data) {
					fSaveDeptResult(data);
				};
				
				callAjax("/system/saveDept.do", "post", "json", true, $("#myForm").serialize() , resultCallback);
			}
			
			/** 부서코드 저장 콜백 함수 */
			function fSaveDeptResult(data) {
				
				// 목록 조회 페이지 번호
				var currentPage = "1";
				
				if ($("#action").val() != "I") {
					currentPage = $("#currentPage").val();
				}
				
				if (data.result == "SUCCESS") {
					
					// 응답 메시지 출력
					swal(data.resultMsg);
					
					// 모달 닫기
					gfCloseModal();
					
					// 목록 조회
					deptManagementList(currentPage);
					
				} else {
					// 오류 응답 메시지 출력
					swal(data.resultMsg);
				}
				
				// 입력폼 초기화
				fInitFormDept();
			}
			
			/** 부서코드 삭제 */
			function fDeleteDept() {
				
				swal("정말 삭제하겠습니까? \n삭제시 복구불가합니다.", {
					  buttons:{
						  yes : "예",
						  no : "아니오"
					  }
					}).then((value) => {  /* 이건 이클립스가 게을러서 뜨는 에러!  */
						switch(value){
						case "yes":
							
							var resultCallback = function(data) {
							fDeleteDeptResult(data);
							};
						
							callAjax("/system/deleteDept.do", "post", "json", true, $("#myForm").serialize(), resultCallback);
						
							break;
						case "no": 
							break;
						}
					});				
				
			}
			

			/** 그룹코드 삭제 콜백 함수 */
			function fDeleteDeptResult(data) {
				
				var currentPage = $("#currentPage").val();
				
				if (data.result == "SUCCESS") {
					
					// 응답 메시지 출력
					swal(data.resultMsg);
					
					// 모달 닫기
					gfCloseModal();
					
					// 그룹코드 목록 조회
					deptManagementList(currentPage);
					
				} else {
					swal(data.resultMsg);
				}	
			}
</script>


</head>
<body>
	<!-- ///////////////////// html 페이지  ///////////////////////////// -->

<form id="myForm" action="" method="">
	
	<input type="hidden" id="currentPage" value="1">  <!-- 현재페이지는 처음에 항상 1로 설정하여 넘김  -->	
	<input type="hidden" name="action" id="action" value=""> 
	
	<div id="wrap_area">

		<h2 class="hidden">header 영역</h2>
		<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

		<h2 class="hidden">컨텐츠 영역</h2>
		<div id="container">
			<ul>
				<li class="lnb">
					<!-- lnb 영역 --> 
					<jsp:include page="/WEB-INF/view/common/lnbMenu.jsp"></jsp:include> <!--// lnb 영역 -->
				</li>
				<li class="contents">
					<!-- contents -->
					<h3 class="hidden">contents 영역</h3> <!-- content -->
					<div class="content">

						<p class="Location">
							<a href="#" class="btn_set home">메인으로</a> 
							<a href="#" class="btn_nav bold">시스템 관리</a> 
								<span class="btn_nav bold">부서관리</span> 
								<a href="#" class="btn_set refresh">새로고침</a>
						</p>
					<p class="conTitle">
							<span> 부서관리 </span> 
						</p>
						
					<!--검색창  -->
					<div class="conTitle" style="margin: 0 25px 10px 0; " align=right>
								
						    <!-- enter입력하면 검색실행   -->
						    <span>부서명</span>
							<input type="text" style="width: 160px; height: 30px;" id="deptname" name="deptname" onkeypress="if( event.keyCode == 13 ){deptManagementList();}">  
							<a href="javascript:deptManagementList()" class="btnType blue" id="searchBtn" name="btn" ><span>검  색</span></a>
							
							<a href="javascript:fModalDept()" class="btnType blue"  name="modal" ><span>부서 등록</span></a> 		
					</div>
					
						
						<div class="deptManagementList">
							<table class="col">
								<caption>caption</caption>
	
		                            <colgroup>
						                   <col width="50px">
						                   <col width="50px">
					                 </colgroup>
								<thead>
									<tr>
							              <th scope="col">부서코드</th>
							              <th scope="col">부서명</th>						              
									</tr>
								</thead>
								<tbody id="deptManagementList"></tbody>
							</table>
							
							<!-- 페이징 처리  -->
							<div class="paging_area" id="pagingnavi">
							</div>
											
						</div>

		
					</div> <!--// content -->

					<h3 class="hidden">풋터 영역</h3>
						<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
				</li>
			</ul>
		</div>
	</div>

<!-- 모달팝업 -->
	<div id="layer1" class="layerPop layerType2" style="width: 600px;">
		<dl>
			<dt>
				<strong>그룹코드 관리</strong>
			</dt>
			<dd class="content">
				<!-- s : 여기에 내용입력 -->
				<table class="row">
					<caption>caption</caption>
					<colgroup>
						<col width="120px">
						<col width="*">
						<col width="120px">
						<col width="*">
					</colgroup>

					<tbody>
						<tr>
							<th scope="row">부서코드 <span class="font_red">*</span></th>
							<td><input type="text" class="inputTxt p100" name="dept_cd" id="dept_cd" /></td>
							<th scope="row">부서명 <span class="font_red">*</span></th>
							<td><input type="text" class="inputTxt p100" name="dept_name" id="dept_name" /></td>
						</tr>					
					</tbody>
				</table>

				<!-- e : 여기에 내용입력 -->

				<div class="btn_areaC mt30">
					<a href="" class="btnType blue" id="btnSaveDept" name="btn"><span>등록</span></a> 
					<a href="" class="btnType blue" id="btnUpdateDept" name="btn"><span>수정</span></a> 
					<a href="" class="btnType blue" id="btnDeleteDept" name="btn"><span>삭제</span></a> 
					<a href=""	class="btnType gray"  id="btnCloseDept" name="btn"><span>취소</span></a>
				</div>
			</dd>
		</dl>
		<a href="" class="closePop"><span class="hidden">닫기</span></a>
	</div>


</form>

</body>
</html>
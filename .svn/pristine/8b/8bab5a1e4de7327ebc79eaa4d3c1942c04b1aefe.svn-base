<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>SamplePage8</title>
<!-- sweet alert import -->
<script src='${CTX_PATH}/js/sweetalert/sweetalert.min.js'></script>
<jsp:include page="/WEB-INF/view/common/common_include.jsp"></jsp:include>
<!-- sweet swal import -->
                              
<script type="text/javascript">
	var pageSizeinf  = 3;
	var pageBlockSizeinquiry  = 10;
   var noticeareavar;
	
    $(document).ready(function() {
       
    	init();
    	
    	// 공지사항 조회
    	fListInf();
    	
    	// 버튼 이벤트 등록
    	fRegisterButtonClickEvent();
    	
   });

 // 1번 초기화 SI
    function init() {
  	  // 전역 컴포넌트 SI
       Vue.component('my-component',{
              template : '<div>전역 컴포넌트</div>'
          });
       
       noticeareavar = new Vue({
            el: '#divNoticeList',
            
            // 지역 컴포넌트 SI
            components: {
            			'bootstrap-table': BootstrapTable
                             },
                             
            // data는 {{listitem}} => '보간법' 으로 코드 내에서 사용할 변수를 생성해 놓는다. SI
            data: {
            		listitem:[]
            }, 
            // 사용할 함수 SI
            methods:{               
               detailview:function(no){
                  alert(no);
                  fNoticeModal(no);  
               }
            }  
            
          });      
    }  
 
    /** 공지사항 조회 */
    function fListInf(currentPage) {
       
       currentPage = currentPage || 1;
       
       console.log("currentPage : " + currentPage);
                       
       var param = {
                currentPage : currentPage
             ,   pageSize : pageSizeinf
       }
       
       var resultCallback = function(data) {
          fListInfResult(data, currentPage);
       };
       
       //Ajax실행 방식
       //callAjax("Url",type,return,async or sync방식,넘겨준거,값,Callback함수 이름)
       //html로 받을거라 text
       callAjax("/inf/listinfvue.do", "post", "json", true, param, resultCallback);
    }
    
    
    /** 공지사항 조회 콜백 함수 */
    function fListInfResult(data, currentPage) {

       //console.log(data);      
       noticeareavar.listitem = data.notice;
       
       // 총 개수 추출
       var totalCntlistInf = data.noticeCnt;
       var list = $("#selectedInfNo").val();
       // 페이지 네비게이션 생성
       var paginationHtml = getPaginationHtml(currentPage, totalCntlistInf, pageSizeinf, pageBlockSizeinquiry, 'fListInf',[list]);
       //console.log("paginationHtml : " + paginationHtml);
    
       $("#listInfPagination").empty().append( paginationHtml );
     
    }
    
    /*공지사항 상세 조회*/
    function fNoticeModal(noticeNo) {
   
       var param = {noticeNo : noticeNo};
       var resultCallback2 = function(data){
          fdetailResult(data);
       };
       
       callAjax("/system/detailNotice.do", "post", "json", true, param, resultCallback2);
    }
   
    /*  공지사항 상세 조회 -> 콜백함수   */
    function fdetailResult(data){

       if(data.resultMsg == "SUCCESS"){
          //모달 띄우기 
          gfModalPop("#notice");
          
         // 모달에 정보 넣기 
         frealPopModal(data.result);
       
       }else{
          alert(data.resultMsg);
       }
    }
    
    
    /** 버튼 이벤트 등록 */
    function fRegisterButtonClickEvent() {
       $('a[name=btn]').click(function(e) {
          e.preventDefault();

          var btnId = $(this).attr('id');
          switch (btnId) {
             case 'btnClose' :
             gfCloseModal();
             break;
       }
    });
    }
</script>

</head>
<body>
<input type="hidden" id="currentPage" value="1">
<input type="hidden" id="selectedInfNo" value="">

   <!-- 모달 배경 -->
   <div id="mask"></div>

   <div id="wrap_area">

      <h2 class="hidden">header 영역</h2>
      <jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>

      <h2 class="hidden">컨텐츠 영역</h2>
      <div id="container">
         <ul>
            <li class="lnb">
               <!-- lnb 영역 --> <jsp:include
                  page="/WEB-INF/view/common/lnbMenu.jsp"></jsp:include> <!--// lnb 영역 -->
            </li>
            <li class="contents">
               <!-- contents -->
               <h3 class="hidden">contents 영역</h3> <!-- content -->
               <div class="content">

                  <p class="Location">
                     <a href="../dashboard/dashboard.do" class="btn_set home">메인으로</a> <span
                        class="btn_nav bold">Sample</span> <span class="btn_nav bold">SampleTest3
                        </span> <a href="/sampletest/samplepage8.do" class="btn_set refresh">새로고침</a>
                  </p>
                  
                  <br> 
                  
                  
                  <!-- 20220627 -->
                  <p class="conTitle" style="margin-bottom: 1%;">
                        <span>9. 공지 사항(v-for,v-if)    </span> 
                        <span class="fr"> 
                           <a  href="https://vuejsexamples.net/vue-table/" target="_blank">변경된 bootstrap-table</a>
                        </span>
                     </p>
                          <table width="100%" class="col" border=0>
                                    <colgroup>
                               <col width="100%">
                           </colgroup>
                           <tbody>
                                <tr>
                                    <td style="text-align:left" >
                                             1. 테이블 컴포넌트 사용 예<br>
                                             2. v-for,v-if 설명 &nbsp&nbsp&nbsp&nbsp&nbsp<a  href="https://vuejsexamples.com/" target="_blank">VusJS Example</a> &nbsp&nbsp&nbsp&nbsp&nbsp<a  href="https://vuejsexamples.com/" target="_blank">VusJS Example</a> <br>
                                             3. Controller 와 연동 설명
                                    </td>
                                </tr>                        
                             </tbody>
                       </table>                
                       <div id="divNoticeList">
                        <div class="bootstrap-table">
                           <div class="fixed-table-toolbar">
                              <div class="bs-bars pull-left"></div>
                              <div class="columns columns-right btn-group pull-right">   </div>
                           </div>
                           <div class="fixed-table-container" style="padding-bottom: 0px;">
                              <div class="fixed-table-body">      
                                    <table class="col">
                                       <caption>caption</caption>
                                       <colgroup>
                                          <col width="5%">
                                          <col width="70%">
                                          <col width="10%">
                                          <col width="5%">
                                       </colgroup>
                        
                                       <thead>
                                          <tr>
                                              <th scope="col">번호</th>
                                             <th scope="col">제목</th>
                                             <th scope="col">작성일</th>
                                             <th scope="col">작성자</th>
                                          </tr>
                                       </thead>
                                       <tbody id="listInf" v-for="(item,index) in listitem" v-if="listitem.length">
                                             <tr @click="detailview(item.noticeNo)">
                                             <td>{{ item.noticeNo }}</td>
                                             <td>{{ item.noticeTitle }}</td>
                                             <td>{{ item.noticeRegdate }}</td>
                                             <td>{{ item.loginId }}</td>                           
                                             </tr>
                                       </tbody>
                                    </table>
                              </div>
                                       <div>
                                    <div>
                                       <div class="clearfix" />
                                    </div>
                              </div>
                            </div>      
                           </div>
                        </div>            

                        <div class="paging_area"  id="listInfPagination"> </div>
                  
           </div> <!--// content -->

               <h3 class="hidden">풋터 영역</h3>
               <jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
            </li>
         </ul>
      </div>
   </div>

   <!--// 모달팝업 -->
</body>
</html>
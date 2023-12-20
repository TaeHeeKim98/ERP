<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title> 캘린더  </title>

<jsp:include page="/WEB-INF/view/common/common_include.jsp"></jsp:include>
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.3.1/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.3.1/main.min.js'></script>

<!-- fullCalendar js import -->

<script src='${CTX_PATH}/js/fullcalendar/ko.js'></script>
<script src='${CTX_PATH}/js/fullcalendar/moment.js'></script>
<style>
	.fc-toolbar-chunk{
		float:left;
	}
</style>
	
	
<script type="text/javascript">
	var calendarEl;
	var calendar;
	var TaskCalendarList;
	
	document.addEventListener('DOMContentLoaded', function() {
	    	calendarEl = document.getElementById('calendar');
    		calendar = new FullCalendar.Calendar(calendarEl, {
    	    initialView: 'dayGridMonth',
    	    contentHeight: 'auto',
    	    locale : 'ko',
    	    headerToolbar: {
    	      left : '',
    	      center: 'prev title next',
    	      right : 'today'
    	    },
    
	    });
	    calendar.render();
	    
   	});
	
	
</script>


</head>
<body>
	<!-- ///////////////////// html 페이지  ///////////////////////////// -->
<body>

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
							class="btn_nav bold"></span> <span class="btn_nav bold"></span> <a href="/employee/empMgt.do" class="btn_set refresh">새로고침</a>
					</p>

					<p class="conTitle">
						<span>캘린더</span> 
					</p>
					<div id='calendar'></div>
					</div> <!--// content -->
				<h3 class="hidden">풋터 영역</h3>
					<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
			</li>
		</ul>
	</div>
</div>


</body>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<!-- 갯수가 0인 경우  -->
		<c:if test="${totalCnt eq 0 }">
			<tr>
				<td colspan="7">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
		<!-- 갯수가 있는 경우  -->
		<c:if test="${totalCnt > 0 }">
			<c:forEach items="${accTitleList}" var="list">			
				<tr onclick="fn_AccTitle(${list.dac_no})">
						<td>${list.acc_no}</td>
						<td>${list.acc_nm}</td>
						<td>${list.dac_no}</td>
						<td>${list.dac_nm}</td>
						<td>${list.dac_cate}</td>
						<td>${list.dac_con}</td>
						<td>${list.dac_stat}</td>
					<!--   List에 있는 js 함수 호출가능 이거 그대로 가지고 가기 때문에 !!  -->
				</tr>
			</c:forEach>
		</c:if>
		
		<!--   이거 중간에 있으면 table 안먹힘  -->

        <input type="hidden" id="totalCnt" name="totalCnt" value="${totalCnt}"/>












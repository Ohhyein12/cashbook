<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:40px;}
	.top {margin-top:10px;}
</style>
</head>
<body>
	<div class = "container">
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class = "top bottom btn btn-info">가계부</a>
		<h1  class = "bottom top text-center">날짜별 리스트</h1>
		<a class="btn btn-outline-primary float-right" href= "<%=request.getContextPath()%>/TagRankController">이전</a>
		<table class = "table">
			<tr>
				<th>cashDate</th>
				<th>cashbookNo</th>
				<th>kind</th>
				<th>tag</th>
				<th>cash</th>
			</tr>
			<%
				for(Map<String, Object> map : list) {
			%>
					<tr>
						<td><%=map.get("cashDate")%></td>
						<td><%=map.get("cashbookNo")%></td>
						<td><%=map.get("kind")%></td>
						<td><%=map.get("tag")%></td>
						<td><%=map.get("cash")%></td>
					</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>
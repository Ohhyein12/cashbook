<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:30px;}
	.top {margin-top:10px;}
</style>
</head>
<body class = "container-fluid">
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
	<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class = "top bottom btn btn-info">가계부</a>
	<h1 class = "bottom text-center">Tag rank</h1>
	<br>
	<div>
		<a class="btn btn-outline-success" href= "<%=request.getContextPath()%>/TagRankController">전체</a>
		<a class="btn btn-outline-primary" href= "<%=request.getContextPath()%>/TagRankController?kind=수입">수입</a>
		<a class="btn btn-outline-primary" href= "<%=request.getContextPath()%>/TagRankController?kind=지출">지출</a>
		
		<form method = "get" action = "<%=request.getContextPath()%>/DayController">
		<span class = "float-right">
			<input type = "date" name = "beginDate"> ~ <input type = "date" name = "lastDate"> 
			<button type = "submit" class="btn btn-primary">날짜별 검색</button>
		</span>
		</form>
	</div>	
		
	<table class = "table">
		<tr>
			<th>kind</th>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			for(Map<String, Object> map : list) {
		%>
				<tr>
					<td><%=map.get("kind")%></td>
					<td><%=map.get("rank")%></td>
					<td><a href="<%=request.getContextPath()%>/TagByListController?tag=<%=map.get("tag")%>"><%=map.get("tag")%></a></td>
					<td><%=map.get("cnt")%></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>
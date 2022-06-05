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
	.bottom {margin-bottom:80px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container">
		<h1 class = "bottom top text-center">Tag별 리스트</h1>
		<a class="btn btn-outline-success float-right" href="<%=request.getContextPath()%>/TagRankController">이전</a>
		<table class = "table">
			<tr>
				<th>tag</th>
				<th>cashDate</th>
				<th>kind</th>
				<th>cash</th>
				<th>memo</th>
			</tr>
			<%
				for(Map<String, Object> map : list) {
			%>
					<tr>
						<td><%=map.get("tag")%></td>
						<td><%=map.get("cashDate")%></td>
						<td><%=map.get("kind")%></td>
						<td><%=map.get("cash")%></td>
						<td><%=map.get("memo")%></td>
					</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>
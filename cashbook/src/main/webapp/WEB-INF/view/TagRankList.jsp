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
<title>TagList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:30px;}
	.top {margin-top:10px;}
</style>
</head>
<body>
	<div class = "container">
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class = "top bottom btn btn-info">가계부</a>
		<h1 class = "bottom text-center">Tag rank</h1>
		<br>
		<div>
			<form method = "get" action = "<%=request.getContextPath()%>/TagRankController">
				<span class = "float-right">
					<select name="kind">
						<option value="">전체</option>
					<%
						if(request.getAttribute("kind")!=null && request.getAttribute("kind").equals("수입")) { // 수입을 검색했다면
					%>
							<option value ="<%=request.getAttribute("kind")%>" selected><%=request.getAttribute("kind")%></option>
							<option value="지출" >지출</option>					
					<%
						} else if(request.getAttribute("kind")!=null && request.getAttribute("kind").equals("지출")) { // 지출을 검색했다면
					%>
							<option value="수입" >수입</option>
							<option value ="<%=request.getAttribute("kind")%>" selected><%=request.getAttribute("kind")%></option>
					<%
						} else { // 아무것도 검색하지 않았을경우 
					%>
							<option value="수입">수입</option>
							<option value="지출">지출</option>
					<%
						}
					%>
					</select>
					<input type = "date" name = "beginDate" value="<%=request.getAttribute("beginDate")%>"> ~ <input type = "date" name = "lastDate" value="<%=request.getAttribute("lastDate")%>"> 
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
	</div>
</body>
</html>
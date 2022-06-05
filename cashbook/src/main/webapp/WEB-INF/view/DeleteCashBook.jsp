<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cashbookNo = (int)request.getAttribute("cashbookNo");

	System.out.println(cashbookNo +"<--cashbookNo DeleteCashBook.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container">
		<h1 class = "bottom top text-center">가계부 삭제</h1>
		<table class = "table">
			<tr>
				<td>가계부 번호</td>
				<td><input type="number" name="cashbookNo" value="<%=cashbookNo%>"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
		</table>
	</div>
</body>
</html>
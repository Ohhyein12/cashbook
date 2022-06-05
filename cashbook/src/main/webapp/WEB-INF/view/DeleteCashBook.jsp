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
		<form method = "post" action = "<%=request.getContextPath()%>/DeleteCashBookController">
			<h2 class = "bottom top text-center">가계부 삭제</h2>
			<table class = "table table-bordered">
				<tr>
					<th class = "table-active">가계부 번호</th>
					<td><input type="number" name="cashbookNo" readonly = "readonly" value="<%=cashbookNo%>" class="form-control"></td>
				</tr>
				<tr>
					<th class = "table-active"> 계정 비밀번호</th>
					<td><input type="password" name="cashbookPw" class="form-control"></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=cashbookNo%>" class="btn btn-secondary">이전</a>
			<button type ="submit" class="btn btn-outline-dark float-right">삭제하기</button>
		</form>
	</div>
</body>
</html>
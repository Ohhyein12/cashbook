<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateCashBook</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body class = "container-fluid">
<%
		CashBook cashBook = (CashBook)request.getAttribute("cashBook");
		
           // 디버깅
		System.out.println(cashBook +"<--cashBook CashBookOne.jsp");
		
%>
	<h2 class = "bottom top text-center">가계부 정보 수정</h2>
	<form method="post" action="<%=request.getContextPath()%>/UpdateCashBookController">
		<table class="table table-bordered">
			<colgroup>
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tr>
				<th>cashbookNo</th>
				<td><input type="number" name="cashbookNo" readonly="readonly" value=<%=cashBook.getCashbookNo()%>></td>
				
			</tr>
			<tr>
				<th class = "table-active">CashDate</th>
				<td><input type="date" name="cashDate" value=<%=cashBook.getCashDate()%>></td>
			</tr>
			<tr>
				<th class = "table-active">Kind</th>
				<td><input type="radio" name="kind" value=<%=cashBook.getKind()%> checked="checked"><%=cashBook.getKind()%>
					<input type="radio" name="kind" value="수입">수입
					<input type="radio" name="kind" value="지출">지출</td>
			</tr>
			<tr>
				<th class = "table-active">Cash</th>
				<td><input type="number" name="cash" value=<%=cashBook.getCash()%>></td>
			</tr>
			<tr>
				<th class = "table-active">Memo</th>
				<td>
					<textarea rows="4" cols="50" name="memo" class = "form-control"><%=cashBook.getMemo()%></textarea>
				</td>
			</tr>
			<tr>
				<th class = "table-active">UpdateDate</th>
				<td><%=cashBook.getUpdateDate()%></td>
			</tr>
			<tr>
				<th class = "table-active">CreateDate</th>
				<td><%=cashBook.getCreateDate()%></td>
			</tr>
		</table>
		<button type="submit" class = "btn btn-outline-dark">수정</button>
	</form>
	<a href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=cashBook.getCashbookNo()%>" type ="button" class="btn btn-primary">이전</a>
	<a href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
</body>
</html>
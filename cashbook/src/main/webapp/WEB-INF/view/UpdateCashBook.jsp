<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	CashBook cashBook = (CashBook)request.getAttribute("cashBook");
	
	// 디버깅
	System.out.println(cashBook +"<--cashBook CashBookOne.jsp");
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateCashBook</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container">
		<h2 class = "bottom top text-center">가계부 정보 수정</h2>
		<form method="post" action="<%=request.getContextPath()%>/UpdateCashBookController">
			<table class="table table-bordered">
				<colgroup>
					<col width = "20%">
					<col width = "*">
				</colgroup>
				<tr>
					<th class = "table-active">cashbookNo</th>
					<td><input type="number" name="cashbookNo" readonly="readonly" value=<%=cashBook.getCashbookNo()%> class = "form-control"></td>
					
				</tr>
				<tr>
					<th class = "table-active">CashDate</th>
					<td><input type="date" name="cashDate" value=<%=cashBook.getCashDate()%> class = "form-control"></td>
				</tr>
				<tr>
					<th class = "table-active">Kind</th>
					<td>
						<%
							if("수입".equals(cashBook.getKind())) {
						%>
								<input type="radio" name="kind" value=<%=cashBook.getKind()%> checked="checked"><%=cashBook.getKind()%>
								<input type="radio" name="kind" value="지출">지출
						<%	
							} else {
						%>	
								<input type="radio" name="kind" value="수입">수입
								<input type="radio" name="kind" value=<%=cashBook.getKind()%> checked="checked"><%=cashBook.getKind()%>
						<%
							}
						%>						
					</td>
				</tr>
				<tr>
					<th class = "table-active">Cash</th>
					<td><input type="number" name="cash" value=<%=cashBook.getCash()%> class = "form-control"></td>
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
			<button type="submit" class = "float-right btn btn-outline-dark">수정</button>
		</form>
		<a href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=cashBook.getCashbookNo()%>" class="btn btn-secondary">이전</a>
	</div>
</body>
</html>
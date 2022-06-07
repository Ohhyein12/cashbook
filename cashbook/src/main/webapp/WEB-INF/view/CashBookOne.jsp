<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	int m = -1;
	int y = -1;
	if(request.getAttribute("m") != null && !"".equals(request.getAttribute("m"))) {
		m = (int)request.getAttribute("m");
		y = (int)request.getAttribute("y");
	}
	CashBook cashBook = (CashBook)request.getAttribute("cashBook");
	
	// 디버깅
	System.out.println(cashBook +"<--cashBook CashBookOne.jsp");
	System.out.println(m +"<--m CashBookOne.jsp");
		
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container">
		<h2 class = "bottom top text-center">가계부 상세 정보</h2>
		<table class="table table-bordered text-center">
			<colgroup>
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tr>
				<th class = "table-success">가계부 번호</th>
				<td><%=cashBook.getCashbookNo()%></td>
			</tr>
			<tr>
				<th class = "table-success">날짜</th>
				<td><%=cashBook.getCashDate()%></td>
			</tr>
			<tr>
				<th class = "table-success">수입 / 지출</th>
				<td><%=cashBook.getKind()%></td>
			</tr>
			<tr>
				<th class = "table-success">금액</th>
				<td><%=cashBook.getCash()%></td>
			</tr>
			<tr>
				<th class = "table-success">메모</th>
				<td><%=cashBook.getMemo()%></td>
			</tr>
			<tr>
				<th class = "table-success">등록일</th>
				<td><%=cashBook.getCreateDate()%></td>
			</tr>
			<tr>
				<th class = "table-success">수정일</th>
				<td><%=cashBook.getUpdateDate()%></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController?m=<%=m%>&y=<%=y%>" class = "btn btn-secondary">이전</a>
		<span class = "float-right">
			<a href="<%=request.getContextPath()%>/UpdateCashBookController?cashbookNo=<%=cashBook.getCashbookNo()%>" class = "btn btn-outline-success">수정</a> 
			<a href="<%=request.getContextPath()%>/DeleteCashBookController?cashbookNo=<%=cashBook.getCashbookNo()%>" class = "btn btn-outline-success">삭제</a>
		</span>
	</div>	
</body>
</html>
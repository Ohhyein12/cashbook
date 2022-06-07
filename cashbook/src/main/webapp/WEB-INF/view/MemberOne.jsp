<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");

	//디버깅
	System.out.println(member +"<--member MemberOne.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
<body>
	<div class = "container">
		<h1 class = "bottom top text-center">정보 상세보기</h1>
		<table class="table text-center">
			<colgroup>
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tr>
				<th class="table-success">Id</th>
				<td><%=member.getMemberId()%></td>
			</tr>
			<tr>
				<th class="table-success">이름</th>
				<td><%=member.getMemberName()%></td>
			</tr>
			<tr>
				<th class="table-success">주소</th>
				<td><%=member.getMemberAddress()%></td>
			</tr>
			<tr>
				<th class="table-success">성별</th>
				<td><%=member.getMemberGender()%></td>
			</tr>
			<tr>
				<th class="table-success">휴대폰 번호</th>
				<td><%=member.getMemberPhone()%></td>
			</tr>
			<tr>
				<th class="table-success">가입 날짜</th>
				<td><%=member.getCreateDate()%></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="btn btn-secondary">이전</a>
		<span class = "float-right">
			<a href = "<%=request.getContextPath()%>/UpdateMemberController" class="btn btn-outline-success">수정</a>
			<a href = "<%=request.getContextPath()%>/DeleteMemberController" class="btn btn-outline-success">삭제</a>
		</span>
	</div>
</body>
</html>
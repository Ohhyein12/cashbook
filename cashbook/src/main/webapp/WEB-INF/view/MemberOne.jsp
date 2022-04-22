<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
<body class = "container-fluid">
<%
	Member member = (Member)request.getAttribute("member");

	//디버깅
	System.out.println(member +"<--member MemberOne.jsp");
%>
	<h1 class = "bottom top text-center">정보 상세보기</h1>
	<table class="table">
		<colgroup>
			<col width = "20%">
			<col width = "*">
		</colgroup>
		<tr>
			<th class="table-warning">Id</th>
			<td><%=member.getMemberId()%></td>
		</tr>
		<tr>
			<th class="table-warning">이름</th>
			<td><%=member.getMemberName()%></td>
		</tr>
		<tr>
			<th class="table-warning">주소</th>
			<td><%=member.getMemberAddress()%></td>
		</tr>
		<tr>
			<th class="table-warning">성별</th>
			<td><%=member.getMemberGender()%></td>
		</tr>
		<tr>
			<th class="table-warning">휴대폰 번호</th>
			<td><%=member.getMemberPhone()%></td>
		</tr>
		<tr>
			<th class="table-warning">가입 날짜</th>
			<td><%=member.getCreateDate()%></td>
		</tr>
	</table>
	<a href = "<%=request.getContextPath()%>/UpdateMemberController">수정</a>
	<a href = "<%=request.getContextPath()%>/DeleteMemberController">삭제</a>
</body>
</html>
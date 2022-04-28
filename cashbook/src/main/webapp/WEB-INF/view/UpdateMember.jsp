<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body class = "container-fluid">
<%
	Member member = (Member)request.getAttribute("member");

	//디버깅
	System.out.println(member +"<--member UpdateMember.jsp");
%>
	<h1 class = "bottom top text-center">정보 수정하기</h1>
	<form method = "post" action = "<%=request.getContextPath()%>/UpdateMemberController">
		<table class="table table-bordered">
		<colgroup>
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tr>
				<th class="table-success">Id</th>
				<td><input type = "text" name="memberId" readonly = "readonly" value="<%=member.getMemberId()%>"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">이름</th>
				<td><input type = "text" name="memberName" value="<%=member.getMemberName()%>"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">주소</th>
				<td><input type = "text" name="memberAddress" value="<%=member.getMemberAddress()%>"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">성별</th>
				<td><select name = "memberGender" class ="form-control"> 
							<option value="<%=member.getMemberGender()%>" selected="selected"><%=member.getMemberGender()%></option>
							<option value="남">남</option>
							<option value="여">여</option>
						</select></td>
			</tr>
			<tr>
				<th class="table-success">휴대폰 번호</th>
				<td><input type = "text" name="memberPhone" value="<%=member.getMemberPhone()%>"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">가입 날짜</th>
				<td><input type = "text" name="createDate" readonly = "readonly" value="<%=member.getCreateDate()%>"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">현재 비밀번호</th>
				<td><input type = "password" name="memberPw"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">변경할 비밀번호</th>
				<td><input type = "password" name="ChangeMemberPw"  class = "form-control"></td>
			</tr>
			<tr>
				<th class="table-success">비밀번호 확인</th>
				<td><input type = "password" name="CheckMemberPw"  class = "form-control"></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/SelectMemberOneController"  class="btn btn-success">이전</a>
		<button type = "submit"  class="btn btn-outline-success float-right">수정</button>
	</form>
</body>
</html>
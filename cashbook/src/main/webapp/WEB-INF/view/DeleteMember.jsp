<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
	<div class = "container">
		<form method = "post" action = "<%=request.getContextPath()%>/DeleteMemberController">
			<h1 class = "bottom top text-center">계정 탈퇴</h1>
			<table class="table table-bordered">
				<colgroup>
					<col width = "20%">
					<col width = "*">
				</colgroup>
				<tr>
					<th class="table-success">아이디</th>
					<td><input type="text" name="memberId" value="<%=session.getAttribute("sessionMemberId")%>" readonly = "readonly" class = "form-control"></td>
				</tr>
				<tr>
					<th class="table-success">비밀번호</th>
					<td><input type="password" name="memberPw" class = "form-control"></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/SelectMemberOneController" class="btn btn-success">이전</a>
			<button type ="submit" class="btn btn-outline-success float-right">탈퇴하기</button>
		</form>
	</div>
</body>
</html>
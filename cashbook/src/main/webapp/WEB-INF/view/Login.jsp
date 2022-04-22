<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:70px;}
	.top {margin-top:70px;}
</style>
</head>
<body class = "container">
	<form method="post" action="<%=request.getContextPath()%>/LoginController">
	<h1 class = "text-success text-center bottom top">Login</h1>
		<table class = " table table-borderless">
			<tr>
				<td>
					<input type="text" name="memberId" value="Id" class = "form-control">
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" name="memberPw" value="password" class = "form-control">
				</td>
			</tr>
		</table>
		<a href = "<%=request.getContextPath()%>/InsertMemberController" class="btn btn-outline-success">회원가입</a>
		<button type="submit" class = "float-right btn btn-success">로그인</button>
	</form>
</body>
</html>
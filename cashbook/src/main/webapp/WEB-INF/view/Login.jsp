<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<form method="post" action="<%=request.getContextPath()%>/LoginController">
	<h1 class = "text-success">Login</h1>
		<div>
			<input type="text" name="memberId" value="Id">
		</div>
		<div>
			<input type="password" name="memberPw" value="password">
		</div>
		<button type="submit">로그인</button>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "<%=request.getContextPath()%>/DeleteMemberController">
		<h1>계정 탈퇴</h1>
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId" value="<%=session.getAttribute("sessionMemberId")%>" readonly = "readonly"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type ="submit">탈퇴하기</button>
	</form>
</body>
</html>
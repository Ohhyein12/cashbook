<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertMember</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method = "post" action = "<%=request.getContextPath()%>/InsertMemberController">
		<table>
			<tr>
				<th>Id</th>
				<td><input type="text" name="memberId"></td>
			</tr>		
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName"></td>
			</tr>		
			<tr>
				<th>성별</th>
				<td>
					<select name = "memberGender">
						<option value="남">남</option>
						<option value="여">여</option>
					</select>
				</td>
			</tr>		
			<tr>
				<th>주소</th>
				<td><input type="text" name="memberAddress"></td>
			</tr>		
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="memberPhone"></td>
			</tr>		
			<tr>
				<th>Password</th>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>
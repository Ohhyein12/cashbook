<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
	.bottom {margin-bottom:50px;}
	.top {margin-top:20px;}
</style>
</head>
<body>
	<div class = "container">
		<h1 class = "text-center bottom top">회원가입</h1>
		<form method = "post" action = "<%=request.getContextPath()%>/InsertMemberController">
			<table class ="table table-bordered">
				<colgroup>
					<col width = "20%" class="text-center">
					<col width = "*">
				</colgroup>
				<tr>
					<th class = "table-success text-center">Id</th>
					<td><input type="text" name="memberId" class ="form-control"></td>
				</tr>		
				<tr>
					<th class = "table-success text-center">이름</th>
					<td><input type="text" name="memberName" class ="form-control"></td>
				</tr>		
				<tr> 
					<th class = "table-success text-center">성별</th>
					<td>
						<select name = "memberGender" class ="form-control"> 
							<option value="남">남</option>
							<option value="여">여</option>
						</select>
					</td>
				</tr>		
				<tr>
					<th class = "table-success text-center">주소</th>
					<td><input type="text" name="memberAddress" class ="form-control"></td>
				</tr>		
				<tr>
					<th class = "table-success text-center">휴대폰 번호</th>
					<td><input type="text" name="memberPhone" class ="form-control"></td>
				</tr>		
				<tr>
					<th class = "table-success text-center">비밀번호</th>
					<td><input type="password" name="memberPw" class ="form-control"></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/LoginController" class="btn btn-success">이전</a>
			<button type="submit"  class="btn btn-outline-success float-right top">회원가입</button>
		</form>
	</div>
</body>
</html>
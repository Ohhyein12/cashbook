<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Member member = (Member)request.getAttribute("member");

	//디버깅
	System.out.println(member +"<--member UpdateMember.jsp");
%>
	<h1>정보 상세보기</h1>
	<form method = "post" action = "<%=request.getContextPath()%>/UpdateMemberController">
		<table border = "1">
			<tr>
				<th>Id</th>
				<td><input type = "text" name="memberId" readonly = "readonly" value="<%=member.getMemberId()%>"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type = "text" name="memberName" value="<%=member.getMemberName()%>"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type = "text" name="memberAddress" value="<%=member.getMemberAddress()%>"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type = "text" name="memberGender" value="<%=member.getMemberGender()%>"></td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type = "text" name="memberPhone" value="<%=member.getMemberPhone()%>"></td>
			</tr>
			<tr>
				<th>가입 날짜</th>
				<td><input type = "text" name="createDate" readonly = "readonly" value="<%=member.getCreateDate()%>"></td>
			</tr>
			<tr>
				<th>현재 비밀번호</th>
				<td><input type = "text" name="memberPw"></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td><input type = "text" name="ChangeMemberPw"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type = "text" name="CheckMemberPw" ></td>
			</tr>
		</table>
		<button type = "submit">수정</button>
	</form>
</body>
</html>
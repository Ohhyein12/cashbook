<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 다른 페이지의 부분으로 사용되는 페이지-->
   <!-- 메인 메뉴 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<style>
   .nav-wrap { height: 70px; padding: 10px 0 0 15px; box-shadow: 0 6px 20px 0 rgb(0 0 0 / 19%); }
   .logo-a { color:#55b179; font-size:2em; font-weight:500;}
   .logo-a:hover { color:#55b179; text-decoration:none; }
   .rightNav { display:inline-block; margin:15px; }
   .nav-a { color:#787878; margin:0 10px; }
   
    @media (max-width:570px){
       .nav-wrap{ text-align:center; }
       .logo-a{ display:block; }
       .rightNav{ float:none !important; } 
   }
</style>

</head>
<body>
   <div class="container-fluid nav-wrap">
      <a href="<%=request.getContextPath()%>/CashBookListByMonthController" class="logo-a">Calendar</a>
      
      <div class="float-right rightNav">
      <a href="<%=request.getContextPath()%>/SelectMemberOneController?MemberId=<%=session.getAttribute("sessionMemberId")%>">[<%=session.getAttribute("sessionMemberId")%>]</a>님 반갑습니다. &nbsp;&nbsp;
      <a href = "<%=request.getContextPath()%>/LogoutController">로그아웃</a>
      </div>
   </div>
   
</body>
</html>
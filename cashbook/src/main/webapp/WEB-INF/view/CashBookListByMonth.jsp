<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookListByMonth</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class ="container-fluid">
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
		int y = (Integer)request.getAttribute("y");
		int m = (Integer)request.getAttribute("m");
		
		int startBlank = (Integer)request.getAttribute("startBlank");
		int endDay = (Integer)request.getAttribute("endDay");
		int endBlank = (Integer)request.getAttribute("endBlank");
		int totalTd = (Integer)request.getAttribute("totalTd");
		
		System.out.println(list.size() +" <- list.size() CashBookListByMonth.jsp");
		System.out.println(y +" <- y CashBookListByMonth.jsp");
		System.out.println(m +" <- m CashBookListByMonth.jsp");
		
		System.out.println(startBlank +" <- startBlank CashBookListByMonth.jsp");
		System.out.println(endDay +" <- endDay CashBookListByMonth.jsp");
		System.out.println(endBlank +" <- endBlank CashBookListByMonth.jsp");
		System.out.println(totalTd +" <- totalTd CashBookListByMonth.jsp");
	%>
	<h2 class = "text-center" style ="margin-top:20px"><%=y%>년 <%=m%>월</h2>
	<div>
		<%=session.getAttribute("sessionMemberId")%>님 반갑습니다.
		<a href = "<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	</div>
	<div>
		<a href = "<%=request.getContextPath()%>/TagRankController">tags</a>
	</div>
	<div class = "float-right" style="margin-bottom:10px">
		<a class = "btn btn-outline-secondary" href="<%=request.getContextPath()%>/CashBookListByMonthController?y=<%=y%>&m=<%=m-1%>">이전달</a>
		<a class = "btn btn-outline-secondary" href="<%=request.getContextPath()%>/CashBookListByMonthController?y=<%=y%>&m=<%=m+1%>">다음달</a>
	</div>
	<!-- 
		1) 이번날 1일의 요일 firstDayYoil -> startBlank -> 일 0, 월 1, 화 2, ... 토 6
		2) 이번달 마지막날짜 endDay
		3) endBlank -> totalBlank
		4) td의 개수 1 ~ totalBlank
				+		
		5) 가계부 list
		6) 오늘 날짜
	-->
	<table class ="table table-bordered table-striped">
		<thead>
			<tr>
				<th class = "text-danger">일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th class = "text-primary">토</th>
			</tr>
		</thead>
		<tr>
			<%
				for(int i=1; i<=totalTd; i+=1) {
					if((i-startBlank) > 0 && (i-startBlank) <= endDay) {
						String c = "";
						if(i%7==0) {
							c = "text-primary";
						} else if(i%7==1) {
							c = "text-danger";			
						} 
			%>
						<td>
							<span class = "<%=c%>"><%=i-startBlank%></span>
							<a href="<%=request.getContextPath()%>/InsertCashBookController?y=<%=y%>&m=<%=m%>&d=<%=i-startBlank%>" class="btn btn-light float-right">입력</a>
							<br>
							<div>
								<!-- 해당 날짜의 cashbook 목록 출력 -->
								<%
									//해당 날짜의 cashbook 목록 출력
									for(Map map : list) {
										if((Integer)map.get("day") == (i-startBlank)) {
								%>
											<div>
												<a href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=map.get("cashbookNo")%>">상세</a>
												[<%=map.get("kind")%>] 
												<%=map.get("cash")%>원
												<%=map.get("memo")%>...
											</div>
								<%												
										}
									}
								%>
							</div>
						</td>
			<%
					} else {
			%>
						<td>&nbsp;</td>
			<%			
					}
					if(i<totalTd && i%7==0) {
			%>
						</tr><tr><!-- 새로운 행을 추가시키기 위해 -->
			<%			
					}
				}
			%>
		</tr>
	</table>
</div>
</body>
</html>

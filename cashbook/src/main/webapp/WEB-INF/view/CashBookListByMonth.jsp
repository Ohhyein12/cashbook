<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashBookListByMonth</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<jsp:include page ="./upMenu.jsp"></jsp:include>
<style>
   .bottom {margin-bottom:80px;}
   .top {margin-top:30px;}
   .plus {color : #487AB8;}
   .minus {color: #e33950f2;}
	hr {margin-top: 1.5rem;}
</style>
</head>
<body>
   <div class ="container-fluid"> 
      <%
         List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
         
         // 값 들고오기
         int y = (Integer)request.getAttribute("y");
         int m = (Integer)request.getAttribute("m");
         
      
         int startBlank = (Integer)request.getAttribute("startBlank");
         int endDay = (Integer)request.getAttribute("endDay");
         int endBlank = (Integer)request.getAttribute("endBlank");
         int totalTd = (Integer)request.getAttribute("totalTd");
         
         //디버깅
         System.out.println(list.size() +" <- list.size() CashBookListByMonth.jsp");
         System.out.println(y +" <- y CashBookListByMonth.jsp");
         System.out.println(m +" <- m CashBookListByMonth.jsp");
         System.out.println(startBlank +" <- startBlank CashBookListByMonth.jsp");
         System.out.println(endDay +" <- endDay CashBookListByMonth.jsp");
         System.out.println(endBlank +" <- endBlank CashBookListByMonth.jsp");
         System.out.println(totalTd +" <- totalTd CashBookListByMonth.jsp");
      %>
      
      <h2 class = "text-center bottom top"><%=y%>년 <%=m%>월</h2>
      <a href = "<%=request.getContextPath()%>/TagRankController" class="btn btn-outline-info float-left">tags</a>
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
      	<colgroup>
      		<col width="10%">
      		<col width="10%">
      		<col width="10%">
      		<col width="10%">
      		<col width="10%">
      		<col width="10%">
      		<col width="10%">
      	</colgroup>
      	
         <thead>
            <tr class = "text-center">
               <th class = "text-danger">일</th>
               <th>월</th>
               <th>화</th>
               <th>수</th>
               <th>목</th>
               <th>금</th>
               <th class = "text-primary">토</th>
            </tr>
         </thead>
         <tbody>
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
                           <hr>
                           <div>
                              <!-- 해당 날짜의 cashbook 목록 출력 -->
                              <%
                                 for(Map<String, Object> map : list) {
                                    if((Integer)map.get("day") == (i-startBlank)) {
                              %>
                                       <div>
                                          <a href="<%=request.getContextPath()%>/CashBookOneController?cashbookNo=<%=map.get("cashbookNo")%>&m=<%=m%>&y=<%=y%>" class="text-body">
                                          <%
                                          	if("수입".equals(map.get("kind"))) {
                                          %>
                                             	<span class = "plus">[<%=map.get("kind")%>]</span>
                                          <%
                                          	} else {
                                          %>
                                          		<span class = "minus">[<%=map.get("kind")%>]</span>
                                          <%
                                          	}
                                          %>
                                             <%=map.get("cash")%>원
                                             <%=map.get("memo")%>...
                                          </a>
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
         </tbody>
      </table>
   </div>
</body>
</html>
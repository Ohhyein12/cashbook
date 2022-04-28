<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertCashBookForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container-fluid">
		<h1 class = "bottom top">InsertCashBook</h1>
		<form action="<%=request.getContextPath()%>/InsertCashBookController" method="post">
			<table class = "table table-bordered">
				<colgroup>
					<col width = "20%">
					<col width = "*">
				</colgroup>
				<tr>
					<th class = "table-active">cashDate</th>
					<td>
						<input type="text" name="cashDate" value="<%=(String)request.getAttribute("cashDate")%>" readonly="readonly" class = "form-control">
					</td>
				</tr>
				<tr>
					<th class = "table-active">kind</th>
					<td>
						<input type="radio" name="kind" value="수입">수입
						<input type="radio" name="kind" value="지출">지출
					</td>
				</tr>
				<tr>
					<th class = "table-active">cash</th>
					<td><input type="number" name="cash" class = "form-control"></td>
				</tr>
				<tr>
					<th class = "table-active">memo</th>
					<td>
						<textarea rows="4" cols="50" name="memo" class = "form-control"></textarea>
					</td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/CashBookListByMonthController" class = "btn btn-secondary">이전</a>
			<button type="submit" class = "float-right btn btn-outline-dark">입력</button>
		</form>
	</div>
</body>
</html>
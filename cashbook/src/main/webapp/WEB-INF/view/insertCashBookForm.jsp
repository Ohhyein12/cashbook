<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertCashBookForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bottom {margin-bottom:60px;}
	.top {margin-top:50px;}
</style>
</head>
<body>
	<div class = "container">
		<h1 class = "bottom top">insertCashBook</h1>
		<form action="<%=request.getContextPath()%>/InsertCashBookController" method="post">
			<table class = "table table-bordered">
				<tr>
					<td class = "table-active">cashDate</td>
					<td>
						<input type="text" name="cashDate" value="<%=(String)request.getAttribute("cashDate")%>" readonly="readonly" class = "form-control">
					</td>
				</tr>
				<tr>
					<td class = "table-active">kind</td>
					<td>
						<input type="radio" name="kind" value="수입">수입
						<input type="radio" name="kind" value="지출">지출
					</td>
				</tr>
				<tr>
					<td class = "table-active">cash</td>
					<td><input type="number" name="cash" class = "form-control"></td>
				</tr>
				<tr>
					<td class = "table-active">memo</td>
					<td>
						<textarea rows="4" cols="50" name="memo" class = "form-control"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit" class = "btn btn-outline-dark">입력</button>
		</form>
	</div>
</body>
</html>
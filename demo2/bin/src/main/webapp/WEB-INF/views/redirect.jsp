<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Redirect Test</h1>
	
	<div class="container">
		<form action="/redirectProc" method="post">
			<div class="form-group">
				<label for="btn">Rirection Btn</label>
				<button type="button" id="btn" name="btn">Btn</button>
			</div>
		</form>
	</div>
	
	<%@ include file="config/bootstrap_config.jsp" %>
</body>
</html>
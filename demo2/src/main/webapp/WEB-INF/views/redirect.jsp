<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>redirect</title>
<%@ include file="config/bootstrap_config.jsp" %>
</head>
<body>
	<h1>Redirect Test</h1>
	
	<div class="container">
		<form action="/redirectProc" method="post">
			<div class="form-group">
				<label for="textBox">Rirection Btn</label>
				<input type="text" class="form-control" id="textBox" name="textBox" placeholder="test Don't care!">
			</div>
			<button type="submit" class="btn btn-primary">Btn</button>
		</form>
	</div>
	
	
</body>
</html>
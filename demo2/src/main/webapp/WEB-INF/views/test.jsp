<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Test1</h1>
	
	<script type="text/javascript">
	var test = {
		userName = "",
		userEmail = "",
		
		init: function(){
			console.log("javascript Test");
		},
	}	
	
	$(document).ready(function(){
		test.init();
	});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>Img Upload</title>
<%@ include file="config/bootstrap_config.jsp" %>
</head>
<body>
	<!-- 보통은 디비에 데이터를 올리지 않습니다. 안그래도 비싼 스토리지에 굳이 넣을 필요가 없기때문이죠.
	            파일명만 구분가능한 네이밍룰 + 난수화하여 동일한 파일이 없도록 한 후 파일 경로만 디비필드에 저장합니다.
	            그런후에 파일을 보여줄때는 경로를 디비에서 가져와서 해당경로의 이미지를 자바에서 불러온 후 화면에 보여주는 방법을 사용합니다. -->
	<div class="container">
		<!-- <form action="/imgUploadProc" method="post" enctype="multipart/form-data" id="ajaxDataSend"> -->
		<form method="POST" enctype="multipart/form-data" id="ajaxDataSend">
			<div class="form-group">
				<label for="dropdownMenu1">Category</label>
				<div class="dropdown" id="mydropdown">
					<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" name="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
						Select <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
						 <c:forEach var="c" items="${categoryList}">
						 	<li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="${c.cno}">${c.name}</a></li>
				          </c:forEach>
					</ul>
				</div>
			</div>
			<input type="hidden" name="category" id="inputCategory">
			<input type="file" name="file">
			<!-- <button type="submit" id="btnSubmit" class="btn btn-primary">Upload</button> -->
			<button type="submit" id="btnSubmit" class="btn btn-primary" onclick=imgUpload.Send_Evt()>Upload</button>
		</form>
		<script>
			$(".dropdown-menu li a").click(function(){
			  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
			  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
			  $('#inputCategory').val($(this).data('value'));
			});
		</script>
	</div>
	<script>
		var imgUpload = {
				Init: function(){
					// maximum permitted size of 1048576 bytes.
					imgUpload.Get_Evt();
				},
				Send_Evt: function(){
					var form = $('#ajaxDataSend')[0];
					var data = new FormData(form);
					
					$("#btnSubmit").prop("disabled", true);
					console.log("Send_Evt");
					$.ajax({
						type: "POST",
						url: "/ajaxSet",
						dataType: "json",
						data: data, 
						enctype: 'multipart/form-data',
						processData: false, //prevent jQuery from automatically transforming the data into a query string
				        contentType: false,
				        cache: false,
						success: function(response){
							console.log(response);
						},
						error: function(e){
							console.log(e);							
						}
					});
					$("#btnSubmit").prop("disabled", false);
				},
				Get_Evt: function(){
					$.ajax({
						type: "post",
						url: "/ajaxGet",
						dataType: "json",
						success: function(response){
							console.log(response);
						},
						error: function(e){
							console.log(e);
						}
					});
				}
		}
		$(document).ready(function() {
			imgUpload.Init();
		})
	</script>
</body>
</html>
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
	<!-- ������ ��� �����͸� �ø��� �ʽ��ϴ�. �ȱ׷��� ��� ���丮���� ���� ���� �ʿ䰡 ���⶧������.
	            ���ϸ��� ���а����� ���ַ̹� + ����ȭ�Ͽ� ������ ������ ������ �� �� ���� ��θ� ����ʵ忡 �����մϴ�.
	            �׷��Ŀ� ������ �����ٶ��� ��θ� ��񿡼� �����ͼ� �ش����� �̹����� �ڹٿ��� �ҷ��� �� ȭ�鿡 �����ִ� ����� ����մϴ�. -->
	<div class="container">
		<form action="/imgUploadProc" method="post" enctype="multipart/form-data">
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
			<input type="hidden" name="category" id="category">
			<input type="file" name="file">
			<button type="submit" class="btn btn-primary">Upload</button>
		</form>
		<script>
			$(".dropdown-menu li a").click(function(){
			  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
			  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
			  $('#category').val($(this).data('value'));
			});
		</script>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>뉴스 관리 앱</title>

</head>
<body>
	<h2 class="text-center">방명록 수정</h2>
		<div class="card card-body">
			<form method="post" action="/jwbook/new.nhn?action=updateNews" enctype="multipart/form-data" onsubmit="return validateInputs()">
				<table class="table table-bordered">
				<tr>
					<td>작성자</td><td><input id="nameInput" type="text" name="title" class="form-control" value="${news.title}"></td>
				</tr>
				<tr>
					<td>이메일</td><td><input id="emailInput" type="email" name="img" class="form-control" value="${news.img}"></td>
				</tr>
				<tr>
					<td>제 목</td><td><input id="titleInput" type="text" name="content" class="form-control" value="${news.content}"></td>
				</tr>
				<tr>
					<td>비밀번호</td><td><input id="passwordInput" type="password" name="password" class="form-control"></td>
				</tr>
				</table>
				<textarea cols = "50" rows="5" id="contentInput" name ="text" class="form-control">${news.text}</textarea>
				<p class="text-center">
				<br>
				<input type="submit" class="btn btn-success mx-2" onclick="validateInputs2()" value="수정">
				<input type="button" class="btn btn-success mx-2" onclick="location.href='new.nhn?action=deleteNews&aid=${news.aid}'" value="삭제">
				<input type="submit" class="btn btn-success mx-2" onclick="location.href='new.nhn?action=listNews'" value="목록">
				</p>
			</form>
		</div>
		
		<script>
		
		function validateInputs() {
	        var name = document.getElementById("nameInput").value;
	        var email = document.getElementById("emailInput").value;
	        var title = document.getElementById("titleInput").value;
	        var password = document.getElementById("passwordInput").value;
	        var content = document.getElementById("contentInput").value;

	        if (name.trim() === "" || email.trim() === "" || title.trim() === "" || password.trim() === "" || content.trim() === "") {
	            
	            return false;
	        }
	        
	        return true;
	    }
		function validateInputs2() {
	        var name = document.getElementById("nameInput").value;
	        var email = document.getElementById("emailInput").value;
	        var title = document.getElementById("titleInput").value;
	        var password = document.getElementById("passwordInput").value;
	        var content = document.getElementById("contentInput").value;

	        if (name.trim() === "" || email.trim() === "" || title.trim() === "" || password.trim() === "" || content.trim() === "") {
	            alert("입력란을 모두 채워주세요.");
	            
	        }
	    }
		 function closeAddForm() {
			    location.href = "/newsList.jsp";
			  }
		</script>
		
</body>
</html>
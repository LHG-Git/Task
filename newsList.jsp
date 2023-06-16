<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link href = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin = "anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class = "container w-75 mt-5 mx-auto">
		<h2 class="text-center">방명록 목록</h2>
		<hr>
		<table class="table table-bordered">
		
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>이메일</th>
				<th>작성일</th>
				<th>제목</th>
			</tr>
			<c:forEach var="news" items="${newslist}" varStatus="status">
			<tr>
				<td>${news.aid}</td>
				<td>${news.title}</td>
				<td>${news.img}</td>
				<td>${news.date}</td>
				<td><a href="new.nhn?action=getNews&aid=${news.aid}" class="text-decoration-none">${news.content}</a></td>
				
			</tr>
			</c:forEach>
		</table>
		
		<c:if test="${error != null}">
		<div class="alert alert-dange alert-dismissible fade show mt-3">
		에러 발생: ${error}
		<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
		</c:if>
		
		<a href="new.nhn?action=getNews2&aid=${news.aid}" class="text-decoration-none"><button class="btn btn-outline-info d-block mx-auto mb-3" type="button" data-bs-toggle="collapse" data-bs-target = "#addForm" aria-expanded = "false" aria-controls = "addForm:">등록</button></a>
		
		<div class="collapse" id="addForm">
		
		</div>
	</div>
	
	<script>
    function clearInputs() {
        document.getElementById("nameInput").value = "";
        document.getElementById("emailInput").value = "";
        document.getElementById("titleInput").value = "";
        document.getElementById("passwordInput").value = "";
        document.getElementById("contentInput").value = "";
    }
    
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
    
    function closeAddForm() {
        var addForm = document.getElementById("addForm");
        var collapse = new bootstrap.Collapse(addForm);
        collapse.hide();
    }
</script>

</body>
</html>
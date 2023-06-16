<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2 class="text-center">방명록 입력</h2>
    <div class="card card-body">
        <form method="post" action="/jwbook/new.nhn?action=addNews" enctype="multipart/form-data" onsubmit="return validateInputs()">
            <table class="table table-bordered">
                <tr>
                    <td>작성자</td>
                    <td><input id="nameInput" type="text" name="title" class="form-control"></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input id="emailInput" type="email" name="img" class="form-control"></td>
                </tr>
                <tr>
                    <td>제 목</td>
                    <td><input id="titleInput" type="text" name="content" class="form-control"></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input id="passwordInput" type="password" name="password" class="form-control"></td>
                </tr>
            </table>
            <textarea cols="50" rows="5" id="contentInput" name="text" class="form-control"></textarea>
            <p class="text-center">
                <br>
                <button type="submit" class="btn btn-success mx-2">입력</button>
                <button type="button" class="btn btn-success mx-2" onclick="clearInputs()">취소</button>
                <button type="button" class="btn btn-success mx-2" onclick="location.href='new.nhn?action=listNews'">목록</button>
            </p>
        </form>
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
                alert("입력란을 모두 채워주세요.");
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>

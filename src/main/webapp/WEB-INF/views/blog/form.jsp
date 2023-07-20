<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

</head>
<body>
    <div class="container">
        <form action="" method="POST">
            <div class="row">
                <div class="col-3">
                  <label for="writer" class="form-label">글쓴이</label>
                  <input name="writer" type="text" class="form-control" id="writer" value="${username}" readonly>
                </div>
                <div class="col-3">
                  <label for="title" class="form-label">제목</label>
                  <input name="BlogTitle" type="text" class="form-control" id="title" placeholder="제목을 적어주세요">
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                  <label for="content" class="form-label"></label>
                  <textarea name="blogContent" class="form-control" id="content" rows="10"></textarea>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                <input type="submit" class="btn btn-primary" value="글쓰기">
                </div>
            </div>
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

 <style>
    div {
      border: 1px solid black;
    }
 </style>
</head>
<body>
<div class = "container" action="/score/detail" >

    <div class="row first-row" >
      <div class="row">
        <div class="col-1">
          글번호
        </div>
        <div class="col-1">
          ${blogId}
        </div>
        <div class="col-2">
          제목
        </div>
        <div class="col-4">
          ${blogTitle}
        </div>
        <div class="col-1">
          작성자
        </div>
        <div class="col-1">
          ${writer}
        </div>
        <div class="col-1">
          조회수
        </div>
        <div class="col-1">
          ${blogCount}
        </div>
      </div>
    </div>

    <div class="row second-row">
      <div class="col-1">
        작성일
      </div>
      <div class="col-5">
        ${publishedAt}
      </div>
      <div class="col-1">
        수정일
      </div>
      <div class="col-2">
        ${updatedAt}
      </div>
    </div>

    <div class="row third-row">
      <div class="col-1">
        본문
      </div>
      <div class="col-11">
        ${blogContent}
      </div>
    </div>

        <a class="btn btn-primary" href="/blog/list" method="GET">목록</a>

        <div class="row fifth-row">
             <form action="/blog/delete" method="POST">
                <input type="hidden" name="blogId" value="${blogId}">
                <input type="submit" class="btn btn-secondary" value="글삭제">
            </form>
            <form action="/blog/updateform" method="POST">
                <input type="hidden" name="blogId" value="${blogId}">
                <input type="submit" class="btn btn-secondary" value="글수정">
            </form>

        </div>

</div>

</body>
</html>
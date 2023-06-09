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

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>글쓴이</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성일</th>
                    <th>수정일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blog" items="${blogList}">
                    <tr>
                        <td><c:out value="${blog.blogId}"/></td>
                        <td>
                            <a href="/blog/detail/${blog.blogId}">
                                <c:out value="${blog.writer}"/>
                            </a>
                        </td>
                        <td>${blog.blogTitle}</td>
                        <td>${blog.blogContent}</td>
                        <td>${blog.publishedAt}</td>
                        <td>${blog.updatedAt}</td>
                        <td>${blog.blogCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a class="btn btn-primary" href="/blog/insert" method="GET">글쓰기</a>


    </div>
</body>
</html>
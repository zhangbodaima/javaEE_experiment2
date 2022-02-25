<%--
  Created by IntelliJ IDEA.
  User: frozenwhale
  Date: 2021/11/25
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <title>BookInfo</title>
    <style type="text/css">
        /*表单样式*/
        *{
            padding: 0;
            margin: 0;
        }

        .box {
            width: 50%;
            position: absolute;
            left: 50%;
            top: 50%;
            transform:translate(-50%,-50%);/*关键代码*/
        }

    </style>
</head>
<body>
<div class="container">
    <div class = "m-auto" style="width: 63%"  >
        <p style="text-align: center;"><img class="rounded" style="width:240px;height:240px" src="https://github.com/leexinhao/Java_experiment1/blob/main/img/logo.jpg?raw=true"></p>

        <h1 class="text-center">图书列表</h1>
        <br>

        <table class="table">
            <thead>
            <tr>
                <th>编号</th>
                <th>标题</th>
                <th>作者</th>
                <th>出版社</th>
                <th>操作</th>
            </tr>
            </thead>

            <c:forEach items="${bookList}" var="book" varStatus="stc">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.press}</td>
                    <td></td>
                </tr>
            </c:forEach>
            <form method="post" action="book" enctype="application/x-www-form-urlencoded">
                <tr>
                    <td><input type="text" class="form-control" name="id" placeholder="请输入编号"></td>
                    <td><input type="text" class="form-control" name="title" placeholder="请输入标题"></td>
                    <td><input type="text" class="form-control" name="author" placeholder="请输入作者"></td>
                    <td><input type="text" class="form-control" name="press" placeholder="请输入出版社"></td>
                    <td><button type="submit" class="btn btn-primary add">添加</button></td>
                </tr>
            </form>


        </table>

    </div>
</div>


<%--<h2>编号：${book.id}</h2>--%>
<%--<h2>标题：${book.title}</h2>--%>
<%--<h2>作者：${book.author}</h2>--%>
</body>
</html>

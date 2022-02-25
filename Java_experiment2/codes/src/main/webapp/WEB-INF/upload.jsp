<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>--%>
<%--    <title>单文件上传</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<form method="post" action="upload" enctype="multipart/form-data">--%>
<%--    <input type="file" name="file"><br>--%>
<%--    <input type="submit" value="提交">--%>
<%--</form>--%>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>单文件上传</title>
    <style type="text/css">
        /*表单样式*/
        *{
            padding: 0;
            margin: 37px;
        }
        .box {
            width: 700px;
            height: 700px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform:translate(-48%,-60%);/*关键代码*/
        }
        .input{
            font-size: 30px;
            background-color: lightblue;
        }
    </style>
</head>
<body>

<div class="box">
<%--    <form method="post" action="upload" enctype="multipart/form-data">--%>
<%--        <div class="custom-file" style="width: 70%">--%>
<%--            <input type="file" name="file">--%>
<%--            <input type="submit" value="提交">--%>
<%--            <br>--%>
<%--            <h6>如果您点击提交，文件会被上传到D:/test/路径下。</h6>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--    <img class="rounded" style="width:240px;height:240px" src="https://github.com/leexinhao/Java_experiment1/blob/main/img/logo.jpg?raw=true">--%>

    <p style="text-align: center;"><img class="rounded" style="width:240px;height:240px" src="https://github.com/leexinhao/Java_experiment1/blob/main/img/logo.jpg?raw=true"></p>
    <h1 style="margin-left:300px">文件上传</h1>
    <form method="post" action="upload" enctype="multipart/form-data" style="margin-left: auto">
        <input type="file" name="file" class="input"/>
        <br/><br/>
        <input type="submit" value="上传" class="input"/>
    </form>
</div>
</body>
</html>

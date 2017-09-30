<%--
  Created by IntelliJ IDEA.
  User: Yifan
  Date: 2017/9/22
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    System.out.println(basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta charset="utf-8" />
    <title>Admin Page</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<script type="text/javascript">
    value = getCookie('username');
    if(value == null || value ==""){
        alert("Not Log In");
        window.location.href = "<%=basePath%>/login.jsp";
    }else{
//        alert("welcome "+value.split('|')[0]);
//        document.getElementById("001").innerText = "fuck you";
    }

    function addtoFavorite(qid){
        window.location.href="<%=basePath%>/homepage/addquestiontoList?qid="+qid;
        <%--window.location.href="<%=basePath%>/homepage/addquestiontoList?qid="+qid;--%>
    }
    function getCookie(name){
        var strCookie=document.cookie;
        var arrCookie=strCookie.split("; ");
        for(var i=0;i<arrCookie.length;i++){
            var arr=arrCookie[i].split("=");
            if(arr[0]==name)
                return arr[1];
        }
        return "";
    }


</script>
    <div class="container">
        <div class="row">
            <div class="col-md-12" style="margin-bottom: 90px;">
                <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                        </button><b class="navbar-brand" style="color: white;">User Homepage  <small style="color: cornsilk; padding-left:10px;">View Questions</small></b>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <a class="nav-link" style="line-height: 50px; color: #C7DDEF;margin-right: 100px;" href="<%=basePath%>homepage/home">Home Page</a>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <ul class="nav nav-pills nav-stacked">
                        <li role="presentation" class="active"><a href="<%=basePath%>homepage/userHomepage">Category</a></li>
                        <c:forEach items="${catelist}" var="i">
                            <%--<li role="presentation"><a href="<%=basePath%>homepage/searchques?cid=${i.cid}">${i.cate}</a></li>--%>
                            <li role="presentation"><a href="<%=basePath%>homepage/showquestion?cid=${i.cid}">${i.cate}</a></li>
                        </c:forEach>
                        <li role="separator" class="divider"></li>
                        <button class="button btn-success btn-block" onclick="window.location.href='personalList'">View My Question List</button>
                    </ul>

                </div>
                <div class="col-md-8">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Content</th>
                            <th>Answer A</th>
                            <th>Answer B</th>
                            <th>Correct Answer</th>
                            <th>Category</th>
                            <th>Add to List</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${queslist}" var="i" varStatus="status">

                            <tr>
                                <td>${i.qid}</td>
                                <td>${i.content}</td>
                                <td>${i.answera}</td>
                                <td>${i.answerb}</td>
                                <td>${i.correct}</td>
                                <td>${showcatelist[status.index].cate }</td>
                                <%--<td><button class="btn btn-danger" onclick="deleteQues(${i.qid})">Delete</button></td>--%>
                                <td><button class="btn btn-primary" onclick="addtoFavorite(${i.qid})">Add</button></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

<nav style="text-align:center">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="questionmanagement?page=${page-1}">Previous</a></li>
        <li class="page-item"><a class="page-link" href="questionmanagement?page=${page}">${page}</a></li>
        <li class="page-item"><a class="page-link" href="questionmanagement?page=${page+1}">Next</a></li>
    </ul>
</nav>

</body>

</html>

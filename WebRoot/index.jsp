<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<title>Admin Page</title>
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
//            alert(value.split("|")[2]);
            if(value.split("|")[2] != "true"){
                alert("Not Authorized Operation");
                window.location.href = "<%=basePath%>/login.jsp";
            }
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
							</button><b class="navbar-brand" style="color: white;">Admin Page</b>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navbar-right">
								<label id="AdminName" style="line-height: 50px; color: #C7DDEF;margin-right: 100px;">Welcome ${admin}</label>
							</ul>							
						</div>
					</nav>
				</div>
			</div>
			<div class="container-fluid">
	<div class="row" style="padding-top: 50px;">
		<div class="col-md-6">
			<div class="page-header">
				<h1>User Statistics</h1>
			</div>
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Category</th>
						<th>Amount</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>1</td>
						<td>Common User</td>
						<td>${userCount}</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Administrator</td>
						<td>${adminCount}</td>
					</tr>
				</tbody>
			</table> 
			<button type="button" class="btn btn-primary btn-block" onclick="window.location.href='usermanagement'">
				User Management
			</button>
		</div>
		<div class="col-md-6">
			<div class="page-header">
				<h1>Question Statistics</h1>
			</div>
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th>Category</th>
						<th>Amount</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Question Categories</td>
						<td>${cateCount}</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Question Numbers</td>
						<td>${queCount}</td>
					</tr>
				</tbody>
			</table> 
			<button type="button" class="btn btn-primary btn-block" onclick="window.location.href='questionmanagement'">
				Question Management
			</button>
		</div>
	</div>
</div>
		</div>
	</body>
</html>
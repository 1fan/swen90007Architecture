<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	function deleteQues(qid){
		window.location.href="<%=basePath%>/homepage/deleteques?qid="+qid;
	}
	</script>
	
	<script type="text/javascript">
    value = getCookie('username');
    if(value == null || value ==""){
        alert("Not Log In");
        window.location.href = "<%=basePath%>/login.jsp";
    }else{
//        alert("welcome "+value.split('|')[0]);
//        document.getElementById("001").innerText = "fuck you";
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
							</button>
							<b class="navbar-brand" style="color: white;">Welcome 
							<strong>${admin} </strong>
							<small style="color: cornsilk; padding-left:10px;">Question Management</small>
							</b>
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
						  <li role="presentation" class="active"><a href="<%=basePath%>homepage/questionmanagement">Category</a></li>
						  <c:forEach items="${catelist}" var="i">
						  <li role="presentation"><a href="<%=basePath%>homepage/searchques?cid=${i.cid}">${i.cate}</a></li>
						  </c:forEach>
						  <li><button class="btn btn-default navbar-right" data-toggle="modal" data-target="#EditCategory">Show all</button></li>
						</ul>
					</div>
					<button class="alert alert-info" data-toggle="modal" data-target="#AddQuesModal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
								 Add a new question
					</button>
					<div class="col-md-8">
						<c:if test="${error}">
							<div class="alert alert-dismissable alert-danger">
								<button type="button" class="close" data-dismiss="alert">x</button>
								<strong>Sorry! Question ${qid} has just been modified at ${modified_time} by ${modified_by}, please try again later!</strong>
							</div>
						</c:if>
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
									<th>Version</th>
									<th>Modified Time</th>
									<th>Modified By</th>
									<th>Modify</th>
									<th>Delete</th>
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
									<td>${i.version}</td>
									<td>${i.modified_time}</td>
									<td>${i.modified_by}</td>
									<td><button class="btn btn-primary" data-toggle="modal" data-target="#ModifyQuestionModal${i.qid}">Modify</button>
									<div class="modal fade" id="ModifyQuestionModal${i.qid }" role="dialog">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<h4 class="modal-title" id="myModalLabel">Modify a question</h4>
											</div>
											<div class="modal-body">
												<form class="form-horizontal" action="<%=basePath%>homepage/modifyques?qid=${i.qid}" method="post" >
													<div class="form-group">
														<label for="inputName3" class="col-sm-2 control-label">Content</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inpuText3" name="content" value="${i.content}">
														</div>
													</div>
													
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">Option_A</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inputPassword3" name="answera" value="${i.answera}">
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Option_B</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inputEmail3" name="answerb" value="${i.answerb}">
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Answer</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inputEmail3" name="correct" value="${i.correct}">
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Category_id</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inputEmail3" name="cid" value="${i.cid}">
														</div>
													</div>
													<div class="form-group">
														<label for="inputVersion" class="col-sm-2 control-label" >Version</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="version" value="${i.version}" readonly>
														</div>
													</div>	
													<div class="form-group">
														<label for="inputTime" class="col-sm-2 control-label" >Modified Time</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="modified_time" value="${i.modified_time}" readonly>
														</div>
													</div>		
													<div class="form-group">
														<label for="inputBy" class="col-sm-2 control-label" >Modified By</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="modified_by" value="${i.modified_by}" readonly>
														</div>
													</div>																	
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
														<button type="submit" class="btn btn-primary">Submit</button>
													</div>
											   </form>
											</div>
										</div>
									</div>
								</div>
									</td>
									<td><button class="btn btn-danger" onclick="deleteQues(${i.qid})">Delete</button></td>
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
			
			<div class="modal fade" id="EditCategory" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="">Categories</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered table-hover">
							<thead>
							
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Modify</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${catelist}" var="i">
								<tr>
									<td>${i.cid}</td>
									<td>${i.cate}</td>
									<td><button class="btn btn-primary" data-toggle="modal" data-target="#ModifyCateModal${i.cid }">Modify</button>
										<div class="modal fade" id="ModifyCateModal${i.cid }" role="dialog">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
													<h4 class="modal-title" id="myModalLabel">Modify name</h4>
												</div>
											<div class="modal-body">
												<form class="form-horizontal" action="<%=basePath%>homepage/modifycate?cid=${i.cid}" method="post" >
													<div class="form-group">
														<label for="inputName3" class="col-sm-2 control-label">Name</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inpuText3" name="cate" value="${i.cate}">
														</div>
													</div>
																													
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
														<button type="submit" class="btn btn-primary">Submit</button>
													</div>
											   </form>
											</div>
										</div>
									</div>
								</div>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="AddQuesModal" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Add a question</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="<%=basePath%>homepage/addquestion" >
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Content</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inpuText3" name="content">
								</div>
							</div>
							
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Option_A</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputPassword3" name="answera">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Option_B</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputEmail3" name="answerb">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Answer</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputEmail3" name="correct">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Category_id</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputEmail3" name="cid">
								</div>
							</div>
											
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary" >Submit</button>
							</div>
					   </form>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>
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
		function deleteUser(id){
			window.location.href="<%=basePath%>/homepage/deleteuser?id="+id;
		}
	</script>
	
		<div class="container">
			<div class="row">
				<div class="col-md-12" style="margin-bottom: 90px;">
					<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
							</button><b class="navbar-brand" style="color: white;">Admin Page  <small style="color: cornsilk; padding-left:10px;">User Management</small></b>
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
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-2 col-md-offset-2">
								<button class="alert alert-info" data-toggle="modal" data-target="#AddUserModal">
								  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
								  Add a new user
								</button>
							</div>
							<div class="col-md-4 col-md-offset-2">
								<form class="navbar-form navbar-left" action="<%=basePath%>homepage/searchuser" method="post">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Search" name="sname">
									</div>
									<button type="submit" class="btn btn-default" data-toggle="modal" data-target="#SearchResult">Submit</button>
								</form>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>User Name</th>
									<th>Password</th>
									<th>Email</th>
									<th>Authority</th>
									<th>Modify</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${userlist}" var="i">						
								<tr>						
									<td>${i.id}</td>
									<td>${i.name }</td>
									<td>${i.password }</td>
									<td>${i.email }</td>
									<td>${i.authority }</td>
									<td>
									<button class="btn btn-primary" data-toggle="modal" data-target="#ModifyUserModal${i.id }" >Modify</button>
									<div class="modal fade" id="ModifyUserModal${i.id }" role="dialog">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<h4 class="modal-title" id="myModalLabel">Modify a User</h4>
											</div>
											<div class="modal-body">
												<form class="form-horizontal" action="<%=basePath%>homepage/modifyuser?id=${i.id}" method="post" >
													<div class="form-group">
														<label for="inputName3" class="col-sm-2 control-label">Username</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inpuText3" name="username" value="${i.name}">
														</div>
													</div>
													
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="inputPassword3" name="password" value="${i.password}">
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
														<div class="col-sm-10">
															<input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email" value=${i.email}>
														</div>
													</div>
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">Authority</label>
														<div class="col-sm-10">
															<select class="form-control" name="authority">
															<c:if test="${i.authority}">														
																<option value="0">Common User</option>	
																<option value="1" selected>Administrator</option>
															</c:if>
															<c:if test="${i.authority != true}">	
																<option value="0" selected>Common User</option>
																<option value="1">Administrator</option>
															</c:if>
															</select>
														</div>
													</div>
																	
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
														<button type="submit" class="btn btn-primary")>Submit</button>
													</div>
											   </form>
											</div>
										</div>
									</div>
								</div></td>
									<td><button class="btn btn-danger" onclick="deleteUser(${i.id})">Delete</button></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<nav style="text-align:center">
			<ul class="pagination">
			    <li class="page-item"><a class="page-link" href="usermanagement?page=${page-1}">Previous</a></li>
			    <li class="page-item"><a class="page-link" href="usermanagement?page=${page}">${page}</a></li>
			    <li class="page-item"><a class="page-link" href="usermanagement?page=${page+1}">Next</a></li>
			</ul>
			</nav>
		</div>
		
		<div class="modal fade" id="AddUserModal" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Add a User</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="<%=basePath%>homepage/adduser" >
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Username</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inpuText3" placeholder="Username" name="username">
								</div>
							</div>
							
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Authority</label>
								<div class="col-sm-10">
									<select class="form-control" name="authority">
										<option value="0">Common User</option>
										<option value="1">Administrator</option>
									</select>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>Login</title>
    </head>
    <body>
    <%
        Object message = request.getAttribute("message");
        if(message!=null && !"".equals(message)){
    %>
    <script type="text/javascript">
        alert("<%=message%>");
    </script>
    <%} %>
        <div class="container">
            <div class="row">
                <div class="col-xs-6 col-sm-4"></div>
                <div class="col-xs-6 col-sm-4">
                    <div class="form-group" style="margin-top: 30px">
                        <div class="row">
                            <div class="col-md-9">
                                <h1>
                                    <strong style="line-height: 80px;font-family: '';">Log In Page </strong>
                                </h1>
                            </div>
                        </div>
                    </div>
                    <form action="<%=basePath%>homepage/login?time=first" method="post" class="form-group" style="margin-top: 20px">
                        <label for="inputName">
                        UserName:
                        </label>
                        <input type="text" name="username" id="username" value="" class="form-control" required="required" />
                        <p class="error" id="nameerror"></p>
                        <label for="password">
                            Password
                        </label>
                        <input type="password" name="password" id="password" value="" class="form-control" required="required" />
                        <p class="error" id="pswerror"></p>
                        <button class="btn btn-primary" type="submit">Submit</button>
                        <a class="btn btn-danger" href="">Link To RegisterPage</a>
                    </form>

                </div>
            </div>
            <div class="clearfix visible-xs-block"></div>
            <div class="col-xs-6 col-sm-4"></div>
        </div>
    </body>
</html>


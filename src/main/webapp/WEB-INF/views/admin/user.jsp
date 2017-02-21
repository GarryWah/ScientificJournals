<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Journal</title>
</head>
<body>

<div class="row">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="/admin/category">Category</a>
                    <li><a href="/admin/journal">Journal</a></li>
                    <li><a href="/admin/title">Title</a></li>
                    <li class="active"><a href="/admin/user">User</a></li>
                    <li><a href="/logout" class="button-bar">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="row">
    <div class="col-md-3 col-xs-12"></div>
    <div class="col-md-7 col-xs-12">
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <form:form class="form-horizontal" action="/admin/user" method="POST" modelAttribute="user">
                    <custom:hiddenInputs excludeParams="firstName, lastName,login,email,phone"/>
                    <div class="form-group">
                        <div class="form-group">
                            <label for="firstName" class="col-sm-offset-2 col-sm-10"><form:errors path="firstName"/></label>
                        </div>
                        <label for="firstName" class="col-sm-2 control-label">First name</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="firstName" id="firstname"/>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-offset-2 col-sm-10"><form:errors path="lastName"/></label>
                        </div>
                        <label for="lastName" class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="lastName" id="lastname"/>
                        </div>
                        <div class="form-group">
                            <label for="username" class="col-sm-offset-2 col-sm-10"><form:errors path="username"/></label>
                        </div>
                        <label for="username" class="col-sm-2 control-label">Login</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="username" id="username"/>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-offset-2 col-sm-10"><form:errors path="email"/></label>
                        </div>
                        <label for="email" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="email" id="login"/>
                        </div>
                        <label for="phone" class="col-sm-2 control-label">Phone number</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="phone" id="phone"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" name="action" value="save" class="btn btn-default">Save</button>
                            <button type="submit" name="action" value="search" class="btn btn-default">Search by login</button>
                            <a class="btn btn-primary" href="/admin/user/cancel<custom:allParams/>">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-xs-2"><h3>Login</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Email</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Phone</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Bucket</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Update</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
        </div>
        <c:forEach items="${users.content}" var="user">
            <div class="row">
                <div class="col-md-2 col-xs-2">${user.username}</div>
                <div class="col-md-2 col-xs-2">${user.email}</div>
                <div class="col-md-2 col-xs-2">${user.phone}</div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/user/order/${user.id}">Bucket</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/user/update/${user.id}<custom:allParams/>">update</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/user/delete/${user.id}<custom:allParams/>">delete</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12">
        <div class="row">
            <div class="col-md-6 col-xs-6 text-center">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <custom:sort innerHtml="Firstname asc" paramValue="firstName"/>
                        <custom:sort innerHtml="Firstname desc" paramValue="firstName,desc"/>
                        <custom:sort innerHtml="Lastname asc" paramValue="lastName"/>
                        <custom:sort innerHtml="Lastname desc" paramValue="lastName,desc"/>
                        <custom:sort innerHtml="Login asc" paramValue="login"/>
                        <custom:sort innerHtml="Login desc" paramValue="login,desc"/>
                        <custom:sort innerHtml="Email asc" paramValue="email"/>
                        <custom:sort innerHtml="Email desc" paramValue="email,desc"/>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-xs-6 text-center">
                <custom:size posibleSizes="1,2,3,4,5,10" size="${users.size}" />
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${users}" cell="<li></li>" container="<ul class='pagination'></ul>" />
    </div>
</div>
</div>
</body>
</html>
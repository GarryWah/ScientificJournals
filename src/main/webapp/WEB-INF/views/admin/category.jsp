<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Category</title>
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
                    <li class="active"><a href="/admin/category">Category</a>
                    <li><a href="/admin/journal">Journal</a></li>
                    <li><a href="/admin/title">Title</a></li>
                    <li><a href="/admin/user">User</a></li>
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
                <form:form class="form-horizontal" action="/admin/category" method="POST" modelAttribute="form">
                    <custom:hiddenInputs excludeParams="name"/>
                    <div class="form-group">
                        <label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="name" id="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <a class="btn btn-primary" href="/admin/category/cancel<custom:allParams/>">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 col-xs-3"><h3>Category</h3></div>
            <div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
            <div class="col-md-3 col-xs-3"><h3>Update</h3></div>
            <div class="col-md-3 col-xs-3"><h3>Show titles</h3></div>
        </div>
        <c:forEach items="${categories.content}" var="category">
            <div class="row">
                <div class="col-md-3 col-xs-3">${category.name}</div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger"
                                                  href="/admin/category/delete/${category.id}<custom:allParams/>">delete</a>
                </div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger"
                                                  href="/admin/category/update/${category.id}<custom:allParams/>">update</a>
                </div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger"
                                                  href="/admin/category/show/title/${category.id}<custom:allParams/>">Show
                    journal titles</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12">
        <div class="row">
            <div class="col-md-6 col-xs-6 text-center">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span
                            class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <custom:sort innerHtml="Name asc" paramValue="name"/>
                        <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                    </ul>
                </div>
            </div>
            <div class="col-md-6 col-xs-6 text-center">
                <custom:size posibleSizes="1,2,4,5,10" size="${categories.size}"/>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-xs-12 text-center">
        <custom:pageable page="${categories}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
    </div>
</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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
        <div class="col-md-12 col-xs-12 text-center"><h5>${category.name}</h5></div>
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <form:form class="form-horizontal" action="/admin/category/show/title/${category.id}" method="POST" modelAttribute="TitleToCategory">

                    <div class="form-group">
                        <label for="name" class="col-sm-offset-2 col-sm-10"><form:errors path="name"/></label>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Journal Title</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="name" id="name"/>
                        </div>
                        <form:hidden input="${title.id}" path="id" id="id"/>
<%--                        <label for="id" class="col-sm-2 control-label">Title id</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="id" id="id"/>
                        </div>--%>
                        <label for="description" class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="description" id="description"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <a class="btn btn-primary" href="/admin/category/show/title/${category.id}/cancel">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <div class="col-md-3 col-xs-3"><h3>Title name</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Update</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Volumes</h3></div>

        <c:forEach items="${category.titles}" var="title">
            <div class="row">
                <div class="col-md-3 col-xs-3">${title.name}</div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/category/show/title/${category.id}/update/${title.id}">update</a></div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/category/show/title/${category.id}/delete/${title.id}">delete</a></div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/title/show/journal/${title.id}">Show volumes</a></div>

            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>

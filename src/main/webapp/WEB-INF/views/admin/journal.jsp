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
                    <li class="active"><a href="/admin/journal">Journal</a></li>
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
                <form:form class="form-horizontal" action="/admin/journal" method="POST" modelAttribute="journal">
                <custom:hiddenInputs excludeParams="volume, year, price, title"/>
                <div class="form-group">
                    <label for="volume" class="col-sm-offset-2 col-sm-10"><form:errors path="volume"/></label>
                </div>
                <div class="form-group">
                    <label for="volume" class="col-sm-2 control-label">Volume</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="volume" id="volume"/>
                    </div>
                    <label for="year" class="col-sm-offset-2 col-sm-10"><form:errors path="year"/></label>
                    <label for="year" class="col-sm-2 control-label">Year</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="year" id="year"/>
                    </div>

                    <label for="price" class="col-sm-offset-2 col-sm-10"><form:errors path="price"/></label>

                    <label for="price" class="col-sm-2 control-label">Price</label>
                    <div class="col-sm-10">
                        <form:input class="form-control" path="price" id="price"/>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">Journal Title</label>
                        <div class="col-sm-10">
                            <form:select path="title" class="form-control">
                                <form:option value="0" label="--Select title"></form:option>
                                <form:options items="${titles}" itemLabel="name" itemValue="id" id="title"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <a class="btn btn-primary" href="/admin/journal/cancel<custom:allParams/>">Cancel</a>
                        </div>
                    </div>
                    </form:form>
                </div>
            </div>
            <div class="col-md-2 col-xs-2"><h3>Title</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Category</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Volume</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Update</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Price</h3></div>
            <c:forEach items="${journals.content}" var="journal">
                <div class="row">
                    <div class="col-md-2 col-xs-2">${journal.title.name}</div>
                    <div class="col-md-2 col-xs-2">${journal.title.category.name}</div>
                    <div class="col-md-2 col-xs-2">Volume ${journal.volume}, ${journal.year} </div>
                    <div class="col-md-2 col-xs-2"><a class="btn btn-danger"
                                                      href="/admin/journal/delete/${journal.id}<custom:allParams/>">delete</a>
                    </div>
                    <div class="col-md-2 col-xs-2"><a class="btn btn-danger"
                                                      href="/admin/journal/update/${journal.id}<custom:allParams/>">update</a>
                    </div>
                    <div class="col-md-2 col-xs-2">${journal.price} $</div>
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
                            <custom:sort innerHtml="Volume asc" paramValue="volume"/>
                            <custom:sort innerHtml="Volume desc" paramValue="volume,desc"/>
                            <custom:sort innerHtml="Year asc" paramValue="year"/>
                            <custom:sort innerHtml="Year desc" paramValue="year,desc"/>
                            <custom:sort innerHtml="Title asc" paramValue="title.name"/>
                            <custom:sort innerHtml="Title desc" paramValue="title.name,desc"/>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 col-xs-6 text-center">
                    <custom:size posibleSizes="1,2,4,5,10" size="${journals.size}"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12 text-center">
            <custom:pageable page="${journals}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
        </div>
    </div>
</div>
</body>
</html>

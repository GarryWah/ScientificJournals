<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


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
                <li class="active"><a href="/user/title">Journal Titles</a></li>
                <li><a href="/user/category">Categories</a></li>
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
            </div>
            <div class="row">
                <div class="col-md-2 col-xs-2"><h3>Bucket</h3></div>
                <div class="col-md-2 col-xs-2"><h3>Category</h3></div>
                <div class="col-md-2 col-xs-2"><h3>Description</h3></div>

            </div>
            <c:forEach items="${titles.content}" var="title">
                <div class="row">
                    <div class="col-md-2 col-xs-2">${title.name}</div>
                    <div class="col-md-2 col-xs-2">${title.category.name}</div>
                    <div class="col-md-2 col-xs-2"> ${title.description}</div>
                    <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/user/title/show/journal/${title.id}<custom:allParams/>">Show volumes</a></div>
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
                            <custom:sort innerHtml="Name asc" paramValue="name"/>
                            <custom:sort innerHtml="Name desc" paramValue="name,desc"/>
                            <custom:sort innerHtml="Category asc" paramValue="category.name"/>
                            <custom:sort innerHtml="Category desc" paramValue="category.name,desc"/>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 col-xs-6 text-center">
                    <custom:size posibleSizes="1,2,3,4,5,10" size="${titles.size}" />
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12 text-center">
            <custom:pageable page="${titles}" cell="<li></li>" container="<ul class='pagination'></ul>" />
        </div>
    </div>
</div>
</body>
</html>

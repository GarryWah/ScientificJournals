<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

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
        <div class="col-md-12 col-xs-12 text-center"><h5>${journalTitle.name}</h5></div>
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <form:form class="form-horizontal" action="/admin/title/show/journal/${journalTitle.id}" method="POST"
                           modelAttribute="Add">
                    <div class="form-group">
                        <label for="volume" class="col-sm-offset-2 col-sm-10"><form:errors path="volume"/></label>
                    </div>
                    <div class="form-group">
                        <label for="volume" class="col-sm-2 control-label">Volume</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="volume" id="volume"/>
                        </div>
                        <label for="year" class="col-sm-2 control-label">Year</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="year" id="year"/>
                        </div>
                        <label for="price" class="col-sm-2 control-label">Price</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="price" id="price"/>
                        </div>
                        <form:hidden input="${journal.id}" path="id" id="id"/>
                            <%--                        <label for="id" class="col-sm-2 control-label">Journal id</label>
                                                    <div class="col-sm-10">
                                                        <form:input class="form-control" path="id" id="id"/>
                                                    </div>--%>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Save</button>
                            <a class="btn btn-primary"
                               href="/admin/title/show/journal/${journalTitle.id}/cancel">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <div class="col-md-3 col-xs-3"><h3>Volume</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Update</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
        <div class="col-md-3 col-xs-3"><h3>Price</h3></div>
        <c:forEach items="${journalTitle.journals}" var="journal">
            <div class="row">
                <div class="col-md-3 col-xs-3">Volume ${journal.volume}, ${journal.year} </div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger"
                                                  href="/admin/title/show/journal/${journalTitle.id}/update/${journal.id}">update
                    volume</a></div>
                <div class="col-md-3 col-xs-3"><a class="btn btn-danger"
                                                  href="/admin/title/show/journal/${journalTitle.id}/delete/${journal.id}">delete
                    volume</a></div>
                <div class="col-md-3 col-xs-3">${journal.price} $</div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>


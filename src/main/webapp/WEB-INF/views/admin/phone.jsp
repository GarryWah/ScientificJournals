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
                    <li><a href="/admin/bucket">Bucket</a></li>
                    <li><a href="/admin/category">Category</a></li>
                    <li class="active"><a href="/admin/phone">Phone</a></li><span
                        class="sr-only">(current)</span></li>
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
        <c:if test="${user ne null}">
        <div class="row">
            <div class="col-md-12 col-xs-12">

                <form:form class="form-horizontal" action="/admin/phone" method="POST" modelAttribute="PhoneForm">
                    <input type="hidden" value="${user.id}" name="userId">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">Number</label>
                        <div class="col-sm-10">
                            <form:input class="form-control" path="number" id="number"/>
                        </div>


                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Create new Phone</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        </c:if>
        <div class="row">
            <div class="col-md-2 col-xs-2"><h3>Phone number</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Add phone</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Update</h3></div>
            <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
        </div>

        <c:forEach items="${phones}" var="phone">
            <div class="row">
                <div class="col-md-2 col-xs-2">${phone.number}</div>

                <div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/admin/phone/add/${phone.id}">Add</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-warning" href="/admin/phone/update/${phone.id}">update</a></div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/phone/delete/${phone.id}">delete</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>
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
                    <li class="active"><a href="/admin/bucket">User Bucket</a></li>
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
        <div class="col-md-12 col-xs-12 text-center"><h2>${user.username}</h2></div>
        <div class="row">
            <div class="col-md-12 col-xs-12">
            </div>
        </div>

        <div class="col-md-2 col-xs-2"><h3>Title</h3></div>
        <div class="col-md-2 col-xs-2"><h3>Volume</h3></div>
        <div class="col-md-2 col-xs-2"><h3>Year</h3></div>
        <div class="col-md-2 col-xs-2"><h3>Price</h3></div>
        <div class="col-md-2 col-xs-2"><h3>Date</h3></div>
        <div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
        <c:forEach items="${user.orders}" var="order">
            <div class="row">
                <div class="col-md-2 col-xs-2">${order.journal.title.name}</div>
                <div class="col-md-2 col-xs-2">${order.journal.volume}</div>
                <div class="col-md-2 col-xs-2">${order.journal.year}</div>
                <div class="col-md-2 col-xs-2">${order.journal.price}</div>
                <div class="col-md-2 col-xs-2">${order.dateOfPay}</div>
                <div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/user/delete/order/${user.id}/${order.id}">delete</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>

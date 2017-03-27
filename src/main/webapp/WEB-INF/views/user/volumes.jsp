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
                    <li><a href="/user/title">Journal Titles</a></li>
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
        <div class="col-md-12 col-xs-12 text-center"><h1>${journalTitle.name}</h1></div>
        <div class="col-md-4 col-xs-4"><h3>Volume</h3></div>
        <div class="col-md-4 col-xs-4"><h3>Price</h3></div>
        <div class="col-md-4 col-xs-4"><h3>Buy</h3></div>
        <c:forEach items="${journalTitle.journals}" var="journal">
            <div class="row">
                <div class="col-md-4 col-xs-4">Volume ${journal.volume}, ${journal.year} </div>
                <div class="col-md-4 col-xs-4">${journal.price} $</div>
                <div class="col-md-4 col-xs-4"><a class="btn btn-danger"
                                                  href="/user/title/show/journal/${id}/${journal.id}">Buy</a></div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-2 col-xs-12"></div>
</div>



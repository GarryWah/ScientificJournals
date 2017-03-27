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
<div class="row">
    <div class="col-sm-12 col-xs-12">
        <form:form class="form-horizontal" action="/login" method="POST">
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login</label>
                <div class="col-sm-8">
                    <input class="form-control" name="login" id="login">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-8">
                    <input class="form-control" name="password" id="password">
                </div>
            </div>
            <div class="form-group">
                <label for="checkbox" class="col-sm-4 control-label">Remember me</label>
                <div class="col-sm-2">
                    <input class="form-control" name="remember-me" type="checkbox" id="checkbox">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default">Log in</button>
                    <div class="col-sm-8">
                        <a class="btn btn-warning" href="/registration">register</a>
                        <a class="btn btn-danger" href="/logout">logout</a>
                    </div>
                </div>
            </div>

        </form:form>
    </div>
</div>
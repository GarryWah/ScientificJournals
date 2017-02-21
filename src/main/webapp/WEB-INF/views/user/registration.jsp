<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="userForm">
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
	<%--		<label for="phone" class="col-sm-2 control-label">Phone number</label>
			<div class="col-sm-10">
				<form:input class="form-control" path="phone" id="phone"/>
			</div>--%>
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<form:password class="form-control" path="password" id="password"/>
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Register</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>Customer form</title>
	<style type="text/css">
		.error {color: red;}
	</style>
</head>
<body>
	<h2>Customer form</h2>
	<form:form action="process-form" modelAttribute="customer">
	First name: <form:input path="firstName"/>
	<br/><br/><br/>
	Last name (*): <form:input path="lastName"/>
	<form:errors path="lastName" cssClass="error"/>
	<br/><br/><br/>
	Free passes: <form:input path="freePasses"/>
	<form:errors path="freePasses" cssClass="error"/>
	<br/><br/><br/>
	Postal code <form:input path="postalCode"/>
	<form:errors path="postalCode" cssClass="error"/>
	<br/><br/><br/>
	<input type="submit" value="Submit">
	</form:form>
</body>
</html>
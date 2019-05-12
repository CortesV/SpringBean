<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Student Registration form</title>
</head>
<body>
	<h2>Student Registration form</h2>
	<form:form action="process-form" modelAttribute="student">
		First name: <form:input path="firstName" />
		<br><br>
		Last name: <form:input path="lastName"/>
		<br><br>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>
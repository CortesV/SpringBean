<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@page import="com.devcortes.spring.mvc.entity.Student"%>
<html>
<head>
<title>Student Confirmation form</title>
</head>
<body>
	<h2>Student Confirmation form</h2>
	First name: <%= ((Student)request.getAttribute("student")).getFirstName() %>
	<br><br>
	Last name: <%= ((Student)request.getAttribute("student")).getLastName() %>
	${student.lastName}
	<br><br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Process form page</title>
</head>
<body>
	<h2>Process form page</h2>
	Student name: <%= request.getParameter("studentName") %>
	<br>
	The message: <%= request.getAttribute("message") %>
</body>
</html>
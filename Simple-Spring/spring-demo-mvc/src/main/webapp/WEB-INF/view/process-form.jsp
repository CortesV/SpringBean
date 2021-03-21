<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Process form page</title>
</head>
<body>
	<h2>Process form page</h2>
	Student name: <%= request.getParameter("studentName") %>
	<br>
	The message: <%= request.getAttribute("message") %>
</body>
</html>
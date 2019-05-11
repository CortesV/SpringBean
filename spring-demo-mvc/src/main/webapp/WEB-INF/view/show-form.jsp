<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show form page</title>
</head>
<body>
	<h2>Show form page</h2>
	<form action="process-form" method="GET">
		<input type="text" name="studentName" placeholder="What's your name?" />
		<input type="submit" />
	</form>
	
	<form action="process-form-version-two" method="GET">
		<input type="text" name="studentName" placeholder="What's your name?" />
		<input type="submit" />
	</form>
</body>
</html>
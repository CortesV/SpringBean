<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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
	
	<form action="process-form-version-three" method="GET">
		<input type="text" name="studentName" placeholder="What's your name?" />
		<input type="submit" />
	</form>
</body>
</html>
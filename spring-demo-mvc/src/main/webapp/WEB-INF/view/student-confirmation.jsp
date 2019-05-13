<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<br><br>
	Country: ${student.country}
	<br><br>
	Favorite language: ${student.favoriteLanguage}
	<br><br>
	Operating Systems: 
	<ul>
		<c:forEach var="temp" items="${student.operatingSystems}">
		<li>${temp}</li>
		</c:forEach>
	</ul>
</body>
</html>
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
		Country:
		<form:select path="country">
			<form:option value="Brazil" label="Brazil"/>
			<form:option value="France" label="France"/>
			<form:option value="USA" label="USA"/>
			<form:option value="Germany" label="Germany"/>
			<form:option value="Ukraine" label="Ukraine"/>
		</form:select>
		<br><br>
		Country options:
		<form:select path="country">
			<form:options items="${student.countryOptions}"/>
		</form:select>
		<br><br>
		Country options from file:
		<form:select path="country">
			<form:options items="${countryOptions}"/>
		</form:select>
		<br><br>
		Java <form:radiobutton path="favoriteLanguage" value="Java"/>
		Kotlin <form:radiobutton path="favoriteLanguage" value="Kotlin"/>
		Groovy <form:radiobutton path="favoriteLanguage" value="Groovy"/>
		C++ <form:radiobutton path="favoriteLanguage" value="C++"/>
		<br><br>
		<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"/>
		<br><br>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>
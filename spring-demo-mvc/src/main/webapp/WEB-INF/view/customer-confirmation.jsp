<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Customer confirmation</title>
</head>
<body>
	<h2>Customer Customer confirmation</h2>
	First name: ${customer.firstName}
	<br/><br/><br/>
	Last name: ${customer.lastName}
	<br/><br/><br/>
	Free passes: ${customer.freePasses}
	<br/><br/><br/>
	Postal code: ${customer.postalCode}
</body>
</html>
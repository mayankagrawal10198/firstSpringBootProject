<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Title</title>
</head>
<body>
<form action="/updateDetails" method="post">
	Enter Your First Name :- <input type="text" name="firstName" value="${user.firstName}"/>
	Enter Your Second Name :- <input type="text" name="lastName" value="${user.lastName}"/>
	Enter Your Email :- <input type="email" name="emailId" value="${user.emailId}"/>
	Enter Your Dob :- <input type="text" name="dob" value="${user.dob}"/>
	<button type="submit">Submit</button>
</form>
</body>
</html>
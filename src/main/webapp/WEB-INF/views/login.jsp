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
<form action="/login" method="post">
	Enter Your Email :- <input type="email" name="emailId"/>
	Enter Your Password :- <input type="password" name="password"/>
	<button type="submit">Submit</button>
</form>
<a href="/signUp" >
	<button>signUp</button>
</a>
</body>
</html>
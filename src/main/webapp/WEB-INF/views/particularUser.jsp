<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<div>
		${user.toString()}
		<a href="/updateUser/${user.emailId}" >
			<button>Update User</button>
		</a>
		<a href="/deleteUser/${user.emailId}" >
			<button>Delete User</button>
		</a>
		<hr/>
	</div>
</body>
</html>
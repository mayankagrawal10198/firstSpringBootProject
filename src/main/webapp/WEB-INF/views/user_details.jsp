<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<c:forEach items="${users}" var="user">
		<div>
				${user.toString()}
				<a href="/update_user/${user.id}" >
					<button>Update User</button>
				</a>
				<a href="/delete_user/${user.id}" >
					<button>Delete User</button>
				</a>
				<hr/>
		</div>
	</c:forEach>
	<a href="/add_user" >
		<button>Add User</button>
	</a>
</body>
</html>
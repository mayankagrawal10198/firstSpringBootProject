<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Title</title>
</head>
<body>
<form action="/newUser" method="post">
	Enter Your First Name :- <input type="text" name="firstName"/>
	Enter Your Second Name :- <input type="text" name="lastName"/>
	Enter Your Email :- <input type="email" name="emailId"/>
	Enter Your Dob :- <input type="text" name="dob"/>
	Enter Your Password :- <input type="password" name="password"/>
	<button type="submit">Submit</button>
</form>
<a href="/login" >
	<button>Already Registered</button>
</a>
</body>
</html>
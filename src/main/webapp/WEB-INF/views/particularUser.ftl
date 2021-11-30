<!DOCTYPE html>
<html>
<head>
	<style>
		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		table.center {
			margin-left: auto;
			margin-right: auto;
		}
		th, td {
			padding: 10px;
			border-color: #96D4D4;
		}
	</style>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<table class="center">
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>DOB</th>
		<th>Created/Updated</th>
		<th colspan="2">Action</th>
	</tr>
	<tr>
		<td>${user.firstName}  ${user.lastName}</td>
		<td>${user.emailId}</td>
		<td>${user.dob}</td>
		<td>${user.date}</td>
		<td>
			<a href="/updateUser/${user.emailId}" >
				<button>Update User</button>
			</a>
		</td>
		<td>
			<a href="/deleteUser/${user.emailId}" >
				<button>Delete User</button>
			</a>
		</td>
	</tr>
</table>
</body>
</html>
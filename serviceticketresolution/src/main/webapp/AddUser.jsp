<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>
</head>
<body>
	<form method="post" action="adduser">
		<h2 align="center">Sevice Ticket Resolution</h2>
		<table align="center">
			<tr>
				<td>Enter Name of Engineer</td>
			</tr>
			<tr>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Enter new Password</td>
			</tr>
			<tr>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Add" /></td>
			</tr>
			<input type="button" value="back" onclick="history.back()">
			<br>
			<br>
		</table>
	</form>
</body>
</html>
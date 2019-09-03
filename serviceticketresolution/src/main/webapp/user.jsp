<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
form {
	background-color: silver;
	text-align: center;
	border-style: solid;
	margin-top: 100px;
	margin-bottom: 100px;
	margin-right: 150px;
	margin-left: 80px;
}

td {
	text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Engineer</title>
</head>
<body bgcolor="gray">
	<form>
		<h1 align="center">Service Ticket Resolution</h1>
		<h2>
			<%out.println("Welcome "); %>${username}
		</h2>
		</br>
		<table align='center'>
			<tr>
				<td><a href="userview">View Ticket</a></br></td>
			</tr>
			<tr>
				<td><a href="raiseticket">Raise Ticket</a></br></td>
			</tr>
		</table>

	</form>
</body>
</html>
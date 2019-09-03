<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
form {
	text-align: center;
	border-style: solid;
	margin-top: 100px;
	margin-bottom: 100px;
	margin-right: 150px;
	margin-left: 80px;
}

td {
	background-color: powderblue;
	text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Engineer</title>
</head>
<body>
	<form>
		<h1 align="center">Service Ticket Resolution</h1>
		<h2>
			<%
				out.println("Welcome ");
			%>${username}
		</h2>
		</br>
		<table align='center'>

			<tr>
				<td><a href="ViewEngineerTickets">View Tickets</a></br></td>
			</tr>
			<tr>
				<td><a href="perseverity">Priority Per Severity</a></br></td>
			</tr>
			<tr>
				<td><a href="perengineer">Engineer Severity</a></br></td>
			</tr>
			<tr>
				<td><a href="ticketage.jsp">Tickets Age</a></br></td>
			</tr>
		</table>

	</form>
</body>
</html>
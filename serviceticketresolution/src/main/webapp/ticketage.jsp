<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<style>
form {
	background-color: powderblue;
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

<title>Age</title>

<body>

	<form method="post" action="ticketname">
		<h2 align="center">Sevice Ticket Resolution</h2>
		<table align="center">
			<tr>
				<td>Enter ticket_id</td>
			</tr>
			<tr>
				<td><input type="number" name="ticketname" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Check" /></td>
			</tr>
			<div>
				<%
					String age = (String) request.getAttribute("age");
					if (age != null)
						out.print(age);
				%>
			</div>
		</table>
	</form>
</body>
</html>
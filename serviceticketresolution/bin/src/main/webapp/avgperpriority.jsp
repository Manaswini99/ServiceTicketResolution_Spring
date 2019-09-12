<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>Priorities Severity</title>
</head>
<body align:center>
<h2>Average Time Taken Per Priority</h2>
<form>
	<br>
	<br>
	<%
		ArrayList<String> list = (ArrayList<String>) request.getAttribute("avg");
	%>
	<p>
		<%
			out.print("Average Timetaken for low Priority tickets:" + list.get(0));
		%></br>
		<%
			out.print("Average Timetaken for Medium Priority tickets:" + list.get(1));
		%></br>
		<%
			out.print("Average Timetaken for High Priority tickets:" + list.get(2));
		%></br>
	</p>
	<br>
	<br>
	</form>
</body>
</html>
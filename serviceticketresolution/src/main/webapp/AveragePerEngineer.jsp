<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
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
<title>Engineers Average Time</title>
</head>
<body>
	<form>
		<h2>Average Time Taken</h2>
		<br> <br>
		<center>
			<table border="1">
				<thead>
					<tr>
						<th>Service Engineer Name</th>
						<th>Average Time</th>
					</tr>
				</thead>
				<%
					List<String> list = (List<String>) request.getAttribute("avgperEngineer");
					for (int i = 0; i < list.size(); i += 2) {
						out.println("<tr><td>" + list.get(i) + "</td><td>" + list.get(i + 1) + "</td></tr>");
					}
				%>
			</table>
		</center>
	</form>
</body>
</html>
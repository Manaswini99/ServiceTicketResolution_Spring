<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body bgcolor="gray">
	<center>
		<h2>Engineers</h2>
	</center>
	<%
		ArrayList<ServiceEngineer> list = (ArrayList<ServiceEngineer>) request.getAttribute("englist");
		if (list.size() > 0) {
	%>
	<table border="1" align="center">
		<thead>
			<tr>
				<th>Service Engineer</th>
				<th>Tickets Worked</th>
				<th>Department</th>

			</tr>
		</thead>
		<%
			}
		%>
		<%
			if (list.size() > 0) {
				for (ServiceEngineer bean : list) {
		%>
		<tr>
			<td><%=bean.getServiceengineer()%></td>
			<td><%=bean.getNo_of_tickets_worked()%></td>
			<td><%=bean.getDepartment().getDepartment_name()%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
	<br>
	<br>
	<center>
		<h2>Users</h2>
	</center>
	<%
		ArrayList<Credentials> userlist = (ArrayList<Credentials>) request.getAttribute("userlist");
		if (list.size() > 0) {
	%>
	<table border="1" align="center">
		<thead>
			<tr>
				<th>Users</th>
			</tr>
		</thead>
		<%
			}
		%>
		<%
			if (list.size() > 0) {
				for (Credentials bean : userlist) {
		%>
		<tr>
			<td><%=bean.getUsername()%></td>

		</tr>
		<%
			}
			}
		%>
	
</body>
</html>
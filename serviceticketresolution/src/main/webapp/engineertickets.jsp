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
}

td {
	text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Engineer</title>
</head>
<body>
	<form method="post">
		<h1 align="center">Service Ticket Resolution</h1>
		<h2>
			<%
				out.println("Welcome " + session.getAttribute("user"));
			%>
		</h2>

		<% ArrayList<TicketBean> list = (ArrayList<TicketBean>) request.getAttribute("engtickets");
			if(list.size()>0)
			{%>
		<table border="1" align="center">
			<thead>
				<tr>
					<th>Ticket Id</th>
					<th>Priority</th>
					<th>Requested EndDate</th>
					<th>Description</th>
					<th>Start Date</th>
					<th>Customer</th>
					<th>Action</th>
				</tr>
			</thead>
			<%} %>
			<%
			if(list.size()>0)	{
			for (TicketBean bean : list) {
				if(bean.getStatus().equals("ongoing") && bean.getService_engineer().getServiceengineer().equals(user)){
			%>
			<tr>
				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getPriority()%></td>
				<td><%=bean.getRequestedend_date()%></td>
				<td><%=bean.getDescription()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getCustomer()%></td>
				<td><a href="completeticket?id=<%=bean.getTicketid()%>">complete</a></td>
			</tr>
			<%}else
				{%>
			<tr>
				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getPriority()%></td>
				<td><%=bean.getRequestedend_date()%></td>
				<td><%=bean.getDescription()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getCustomer()%></td>
				<td>completed</td>
			</tr>
			<%}
				}
			}
			%>
		</table>
		<br> <br>
	</form>
	<form method="post" action="changepriority">
		<label for="ticket"><b>Ticket ID</b></label><input type="text"
			name="ticket" placeholder="Enter ticket_id" /> <select
			name="newpriority">
			<option value="low">Low</option>
			<option value="medium">Medium</option>
			<option value="high">High</option>
		</select>&nbsp; <input type="submit" name="submit" value="set" />
	</form>
</body>
</html>

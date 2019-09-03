<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tickets List</title>
</head>
<center>
	<body>
		<%
List<TicketBean> tickets =(List<TicketBean>) request.getAttribute("ticketlist");
%>
		<%if(tickets.size()>0){ %>
		<table border="1">
			<thead>
				<h3>Solved Tickets</h3>
				<tr>

					<th>Ticket Id</th>
					<th>Issue Date</th>
					<th>Department</th>
					<th>Requested EndDate</th>
					<th>Completed Date</th>


				</tr>
			</thead>


			<%
				
			for (TicketBean bean : tickets) {
				if(bean.getCustomer().equals(session.getAttribute("user"))&&bean.getStatus().equals("completed"))
				{
 %>
			<tr>

				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getDepartment().getDepartment_name()%></td>
				<td><%=bean.getRequestedend_date()%></td>
				<td><%=bean.getCompleted_date()%></td>
			</tr>
			<%
				}
			}
			%>
		</table>
		<table border="1">
			<thead>
				<h3>Unsolved Tickets</h3>
				<tr>

					<th>Ticket Id</th>
					<th>Issue Date</th>
					<th>Department</th>
					<th>Requested EndDate</th>
				</tr>
			</thead>


			<%
				
				
			for (TicketBean bean : tickets) {
				if(bean.getCustomer().equals(session.getAttribute("user"))
						&& bean.getStatus().equals("ongoing")||bean.getStatus().equals("pending"))
				{
 %>
			<tr>

				<td><%=bean.getTicketid()%></td>
				<td><%=bean.getStart_date()%></td>
				<td><%=bean.getDepartment().getDepartment_name()%></td>
				<td><%=bean.getRequestedend_date()%></td>

			</tr>
			<%
				}
			}
			%>
		</table>
		<%} else
		{%>
		No Tickets Raiseed!!
		<%} %>
	<input type="button" value="back" onclick="history.back()">
</center>

<br>
<br>
</body>
</html>
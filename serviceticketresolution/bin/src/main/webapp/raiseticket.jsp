<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<center>
<body>
	<%
		List<DepartmentBean> departments = (List<DepartmentBean>) request.getAttribute("deptlist");
	%>
	<form method="post" action="ticket">
		<h1 align="center">Sevice Ticket Resolution</h1>
		<table>
			<h2 align="center"></h2>
			<h3>Select Issue</h3>
			<select name="department_id">
				<%
					for (DepartmentBean department : departments) {
						out.println("<option value=" + department.getDepartment_id() + ">" + department.getDepartment_name()
								+ "</option>");
					}
				%>
			</select>
			<h3>
				Description:</br>
				<input type="text" name="description" />
			</h3>
			<h3>Requested End Date</h3>
			<input type="date" name="end_date" />
			<h3>Ticket Priority</h3>
			<select name="priority">
				<option value="low">Low</option>
				<option value="medium">Medium</option>
				<option value="high">High</option>
			</select>
			</br>
			<input type="submit" value="submit" />
		</table>
	</form>
</center>
</body>
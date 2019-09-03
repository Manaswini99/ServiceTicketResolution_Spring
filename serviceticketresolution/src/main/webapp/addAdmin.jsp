<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pojo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
table {
	text-align: center;
}

td {
	text-align: left;
}
</style>

<title>Add Engineer</title>

<body>
	<%
		List<DepartmentBean> departments = (List<DepartmentBean>) request.getAttribute("deptlist");
	%>

	<form method="post" action="AddingEngineer">
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
				<td>Select Area of Expertise</td>
			</tr>
			<tr>
				<td><select name="department_id">
						<%
							for (DepartmentBean department : departments) {
								out.println("<option value=" + department.getDepartment_id() + ">" + department.getDepartment_name()
										+ "</option>");
							}
						%>
				</select>
			<tr>
				<td><input type="submit" name="Add" /></td>
			</tr>
		</table>
	</form>
	<form action="admin.jsp">
		<table align="center">
			<tr>
				<td><input type="submit" value="back"><br>
				<br></td>
			</tr>
	</form>
		</table>
</body>
</html>
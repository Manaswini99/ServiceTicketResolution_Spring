<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Service Engineer</title>
</head>
<body>
	<form method="get">
		<h1 align="center">Service Ticket Resolution</h1>
		<h2>
			<%if(request.getAttribute("insertionStatus")!=null)
				{%>
			<%=request.getAttribute("insertionStatus") %>
			<%} %>
			</br>
			<%out.println("Welcome "); %>${username}



		</h2>

		<a href="addeng">Add ServiceEngineer</a>| <a href="AddUser.jsp">Add
			User</a>| <a href="View">View</a></br>



	</form>
</body>
</html>

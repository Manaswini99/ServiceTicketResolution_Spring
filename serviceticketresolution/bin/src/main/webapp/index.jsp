<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${error}
	<form id=login method="post" action="control">
		<br> Username:<input type="text" name="username" /><br> Password:<input
			type="text" name="password" /> <input type="submit" name="submit" />
	</form>
</body>
</html>
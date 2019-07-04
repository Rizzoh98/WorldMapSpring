<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style type="text/css">
body {
	background-image:
		url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div align="center"><h1>LISTA NAZIONI</h1>

	<c:forEach var="Nazione" items="${result}">
		<a href="citys?countrycode=${Nazione.countrycode}">${Nazione.name}</a><br>
	</c:forEach></div>
</body>
</html>
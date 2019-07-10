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


	<div align="center">



		<h1>LISTA CITTA'</h1>
		<br> <a href="./nation">Torna alle Nazioni</a> <br> <br>
		
		<a href="./LoadEditPage?id=0&countrycode=${param.countrycode}">Nuova Città</a>
		
		<br> <br>

		<table align="center">
			<tr>
				<td><a href="./citys?order=${orderName}">${orderName}</a></td>
				<td><a href="./citys?order=${orderPop}">${orderPop}</a></td>
			</tr>

			<c:forEach items="${result}" var="Citta">
				<tr>
					<td>
						<p>${Citta.name}</p>
					</td>

					<td>
						<p>${Citta.population}</p>
					</td>

					<td><a href="./Delete?id=${Citta.id}">Elimina!</a></td>
					<td><a href="./LoadEditPage?id=${Citta.id}&countrycode=${param.countrycode}">Modifica</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
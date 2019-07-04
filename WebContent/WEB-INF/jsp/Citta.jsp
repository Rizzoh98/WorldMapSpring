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



		<h1>LISTA CITTA'</h1><br>
		
		<form action="nation">
			<input type="submit" value="Torna alle Nazioni"> 
		</form><br><br>
		
		<form method = "GET" action="Add">
			<input type="text" name="addcity" value=""> 
			<input type="submit" value="Aggiungi"> 
			<input type="hidden" value="${Citta.name}" name="city"> 
			

		</form><br><br>

		<table align="center">

			<tr>
			
				<td>
					<form action="citys">
						<input type="submit" value="A-Z / Z-A">  
						<input type="hidden" value="${param.countrycode}" name="countrycode">
						<input type="hidden" value="${order}" name="order">
					</form>
				</td>

				<td>
					<form action="citys">
						<input type="submit" value="Max/Min"> 
						<input type="hidden" value="${param.countrycode}" name="countrycode">
						<input type="hidden" value="${orderP}" name="order">
					</form>
				</td>
			
			</tr>


			<c:forEach items="${result}" var="Citta">
				<tr>
					<td>
						<p>${Citta.name}</p>
					</td>

					<td>
						<p>${Citta.population}</p>
					</td>

					<td>
						<form method = "GET" action="Delete">
							<input type="submit" value="Elimina">
							<input type="hidden" value="${Citta.id}" name="city"> 
							<input type="hidden" value="${param.countrycode}" name="countrycode">
						</form>
					</td>

					<td>
						<form method = "GET" action="Update">
							<input type="text" name="renamecity" value=""> 
							<input type="submit" value="Modifica">
							<input type="hidden" value="${Citta.id}" name="city"> 
							<input type="hidden" value="${param.countrycode}" name="countrycode">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
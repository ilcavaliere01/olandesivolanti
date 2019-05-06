<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String error = (String) request.getAttribute("error");
	String URL = (String) request.getServletContext().getInitParameter("URL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>TakeYourCar</title>
		<link type="text/css" rel="stylesheet" href="css/login.css">
	</head>
	
	<body>
		
		<div class="container">
		
			<div class="titolo">Welcome on <div id="take">TakeYourCar</div> !</div> 
			
			<form action="<%=response.encodeURL("login")%>" method="post">
				<div class="box">
					<input type="text" name="username" placeholder="username"><br>
				</div>
				
				<div class="box">
					<input type="password" name="password" placeholder="password"><br>
				</div>
				
				<div class="radio">
					<input type="radio" name="ruolo" value="admin" required> Admin
					<input type="radio" name="ruolo" value="utente" required> Utente
				</div>
				
				<input class="btn" type="submit" value="login">
				
			</form>
			
			<a href="" class="b1">Password dimenticata?</a>
			<a href="" class="b2">Crea un account!</a>
			<%if (error != null) { %>
					<p class="error"><%=error%></p>
				<%  request.getSession().removeAttribute("error");
				}%>
		</div>
	</body>
</html>
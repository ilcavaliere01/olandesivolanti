<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String error = (String) request.getAttribute("error");
	String URL = (String) request.getServletContext().getInitParameter("URL");
	String classe = "container";
	String success = (String) request.getSession().getAttribute("success");
	if (success == null) success = "";
	if (error != null) classe = "shake";
	if (error == null) error = "";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<meta charset="ISO-8859-1">
		<title>TakeYourCar</title>
		<link type="text/css" rel="stylesheet" href="css/login.css">
		<script src="js/login.js" type="text/javascript"></script>
		<script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
		
		<script>			
			function handler(xml) {
				//se lo stato � quello finale e non ci sono errori
				if (xml.readyState == 4 && xml.status == 200) {
					try {
						//converto il JSON in oggetto
						var obj = JSON.parse(xml.responseText);
						//faccio il redirect alla pagina di home
						$(window.location).attr("href", "home");
					} catch(ex) {
						//aggiungo la classe che serve per fare lo shake della form
						$("#contenitore").addClass("shake");
						//imposto il messaggo di errore
						$(".error").html("Username o password errati");
					}
				}
			}
		</script>
	</head>
	
	<body>
		
		<div id="contenitore" class="<%=classe%>">
		
			<div class="titolo">Welcome on <div id="take">TakeYourCar</div> !</div> 
			
			<form action="<%=response.encodeURL("home")%>" method="post">
				<div class="box">
					<input id="username" type="text" name="username" placeholder="username"><br>
				</div>
				
				<div class="box">
					<input id="password" type="password" name="password" placeholder="password"><br>
				</div>
				
				<div class="radio">
					<input id="admin" type="radio" name="ruolo" value="admin" required> Admin
					<input id="utente" type="radio" name="ruolo" value="utente" required> Utente
				</div>
				
				<!-- <input class="btn" type="submit" value="login"> -->
			</form>
			
			<input class="btn" type="submit" value="login" onclick="validateLogin(document.getElementById('username').value, document.getElementById('password').value, handler, document.getElementById('admin'), document.getElementById('utente'))">
			
			<a href="" class="b1">Password dimenticata?</a>
			<a href="register" class="b2">Crea un account!</a>
			<p class="error"><%=error%></p>
			<p class="success"><%=success%></p>
			
			<script>
				
			</script>
		</div>
	</body>
</html>
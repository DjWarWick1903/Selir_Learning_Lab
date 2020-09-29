<%@ page import="bazaDate.Account, bazaDate.AccountManager" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<html>

	<head>
		<title>Totally not Spotify</title>
	</head>

	<style>
		body {
			text-align: center;
			<%
			Account account = (Account)session.getAttribute("cont");
			AccountManager accMan = new AccountManager();
			if(account == null || accMan.getAccountPriviledge(account) == 0) {
				%>
				background-color: papayawhip;
				<%
			} else {
				%>
				background-color: lightcyan;
				<%
			}
			%>
		}

		form {
    		display: inline-block;
		}
		
		label, input {
			display: inline-block;
		}
		
		label {
			width: 30%;
			text-align: centre;
			color: green;
		}
		
		label+input {
			width: 70%;
			
		}
		
		h1, h3 {
			color: #4CAF50;
			font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
		}
		
		h3 {
			font-size: 150%;
			padding: -5px 20px;
		}
		
		h1 {
			font-size: 200%;
		}
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
		}
		
		input[type=text] {
  			width: 100%;
  			padding: 5px 20px;
  			margin: 8px 0;
  			box-sizing: border-box;
  			outline: none;
  			border: 2px solid #4CAF50;
  			font-size: 15px;
		}

		input[type=text]:focus {
  			background-color: #C4EAC4;
		}
		
		input[type=submit] {
  			background-color: #4CAF50;
  			border: none;
  			color: white;
  			padding: 5px 32px;
  			text-decoration: none;
  			margin: 4px 2px;
  			cursor: pointer;
  			width: 160px;
  			font-size: 15px;
		}
	</style>
	
	<body>
		<h1>Totally not Spotify</h1>
		<hr> 
		<h1>- Creare cont -</h1>

		<form action = "Create_account_result.jsp" method = "POST">
			<label for="username">Utilizator:</label> 
			<input type = "text" name = "username"> <br> <br>
			
			<label for="parola">Parola:</label> 
			<input type = "text" name = "parola" > <br> <br>
			
			<input type = "submit" value = "Creeaza" />
		</form>

		<br>

		<form action="MainPage.jsp" method = "POST">
			<input type = "submit" value = "Inapoi" />
		</form>

	</body>

</html>

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
    		background-color: papayawhip;
		}

		form {
    		display: inline-block;
		}
		
		h1, h3 {
			color: #4CAF50;
			font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
		}
		
		h3 {
			font-size: 150%;
		}
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
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

		<%

		String user = request.getParameter("username");
		String pass = request.getParameter("parola");

		Account account = new Account(0, user, pass);
		AccountManager man = new AccountManager();

		boolean exists = man.verifyAccount(account);
		
		if(exists) {
			session.setAttribute("cont", account);
			out.print("<h3> Bine ai venit " + account.getUsername() + "! </h3> <br>");
  		%>
    		<form action="MainPage.jsp" method = "POST">
				<input type = "submit" value = "Continua" />
			</form>
    	<%
		//Daca contul nu a fost gasit, userul va avea optiunea sa se intoarca
		} else {
			%>
			<h3>Ne pare rau dar contul introdus nu exista in baza noastra de date!</h3>
			<h3>Va rugam sa verificati numele si parola introduse sau sa va creati mai intai un cont.</h3>
			<br>
			<form action="Create_account.jsp" method = "POST">
				<input type = "submit" value = "Creeaza cont" />
			</form>
			<form action="Login.jsp" method = "POST">
				<input type = "submit" value = "Inapoi" />
			</form>
			<%
		}

		%>

	</body>

</html>

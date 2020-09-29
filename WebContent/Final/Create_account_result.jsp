<%@ page import="bazaDate.Account, bazaDate.AccountManager, bazaDate.DataCheck" %>
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
				AccountManager man = new AccountManager();
				if(account == null || man.getAccountPriviledge(account) == 0) {
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
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
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
		<hr> <br>
		<%

		String user = request.getParameter("username");
		String pass = request.getParameter("parola");
		
		

		int checkId = DataCheck.checkAccount(user, pass);
		if(!user.equals("") && !pass.equals("")) {
			if(checkId == -1) {
				out.println("<h3>Username-ul si parola trebuie sa se incadreze in maxim 30 de caractere.</h3>");
				%>
				<br>
				<form action="MainPage.jsp" method = "POST">
					<input type = "submit" value = "Inapoi" />
				</form>
				<%
			} else if(checkId == 0) {
				int newId = man.createAccount(account, 0);
				if(newId != 0) {
					out.println("<h3>Contul a fost creat cu succes!</h3>");
					%>
					<br>
					<form action="MainPage.jsp" method = "POST">
						<input type = "submit" value = "Continua" />
					</form>
					<%
				} else {
					out.println("<h3>Serverul a intampinat o eroare la crearea contului.</h3>");
					%>
					<br> <br>
					<form action="Create_account.jsp" method = "POST">
						<input type = "submit" value = "Inapoi" />
					</form>
					<%
				}
			} else if(checkId > 0){
					out.println("<h3>Contul dumneavoastra nu a putut fi creat.</h3>");
					out.println("<h3>Incercati sa folositi un alt username (cel introdus poate fi deja folosit)</h3>");
					%>
					<br> <br>
					<form action="Create_account.jsp" method = "POST">
						<input type = "submit" value = "Inapoi" />
					</form>
					<%
			}
		} else {
			out.println("<h3>Introduceti in casete datele contului pe care doriti sa il creati.</h3>");
			%>
			
			<form action="Create_account.jsp" method = "POST">
				<input type = "submit" value = "Inapoi" />
			</form>
			<%
		}

		%>

	</body>

</html>

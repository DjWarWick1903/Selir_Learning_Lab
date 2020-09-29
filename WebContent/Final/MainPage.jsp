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
		
		<h3>Cautare artisti</h3>
		<form action = "Output_artisti.jsp" method = "POST">
			<input type = "text" name = "artist" placeholder="Artist">
			<input type = "submit" value = "Cauta artist"/>
		</form>

		<br>

		<h3>Cautare albume</h3>
		<form action = "Output_album.jsp" method = "POST">
			<input type = "text" name = "album" placeholder="Album">
			<input type = "submit" value = "Cauta album"/>
		</form>

		<br>

		<h3>Cautare melodii</h3>
		<form action = "Output_melodii.jsp" method = "POST">
			<input type = "text" name = "melodie" placeholder="Melodie">
			<input type = "submit" value = "Cauta melodie"/>
		</form>

		<br>

		<%
			if(account == null) {
				%>
				<form action="Login.jsp" method = "POST">
					<input type = "submit" value = "Login" />
				</form>

				<form action="Create_account.jsp" method = "POST">
					<input type = "submit" value = "Creeaza cont" />
				</form>
				<%
			} else if(accMan.getAccountPriviledge(account) == 0) {
				%>
				<form action="Logout.jsp" method = "POST">
					<input type = "submit" value = "Logout"/>
				</form>
				<%
			} else if(accMan.getAccountPriviledge(account) == 1) {
				%>
				<h3>Functii avansate</h3>

				<form action="Insert_artist.jsp" method = "POST">
					<input type = "submit" value = "Adauga artist" />
				</form>

				<form action = "Delete_artist.jsp" method = "POST">
					<input type = "submit" value = "Sterge artist" />
				</form>

				<br>

				<form action="Logout.jsp" method = "POST">
					<input type = "submit" value = "Logout" />
				</form>

				<form action="Create_account.jsp" method = "POST">
					<input type = "submit" value = "Creeaza cont" />
				</form>
				<%
			}
		%>
	</body>

</html>

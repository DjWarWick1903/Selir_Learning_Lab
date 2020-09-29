<%@ page import = "bazaDate.Account, bazaDate.AccountManager"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<html>

	<head>
		<title>Totally not Spotify</title>
	</head>

	<script>
		<%@include file="Redirect.jsp"%>
	</script>
	
	<style>
		p {
			border:3px;
			border-style:double;
			margin-left: auto;
  			margin-right: 25%;
  			width: 50%;
  			color: #4CAF50;
  			font-size: 120%
		}
		
		body {
    		text-align: center;
    		background-color: lightcyan;
		}

		form {
    		display: inline-block;
		}
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
		}
		
		h1 {
			color: #4CAF50;
			font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
		}
		
		label, input {
			display: inline-block;
		}
		
		label {
			width: 60%;
			text-align: centre;
			color: green;
			font-size: 110%
		}
		
		label+input {
			width: 100%;
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
		<h1>- Introducere artist -</h1>
		
		<p>
		Campul 'Trupa' se va lasa gol daca artistul introdus este de fapt o trupa. In cazul acesta, se aplica acelasi lucru si la campul 'Data nasterii' <br>
		Daca artistul introdus nu apartine niciunei trupe deja existente in baza de date, doar campul 'Trupa' va fi lasat gol.
		</p>
		
		<br>
		
		<form action = "Insert_artist_result.jsp" method = "POST">
			<label for="artist">Nume artist:</label> 
			<br> <input type = "text" name = "artist"> <br> <br>
			
			<label for="trupa">Trupa:</label> 
			<br> <input type = "text" name = "trupa"> <br> <br>
			
			<label for="an">An debut:</label> 
			<br> <input type = "text" name = "an"> <br> <br>
			
			<label for="nationalitate">Nationalitate:</label> 
			<br> <input type = "text" name = "nationalitate"> <br> <br>
			
			<label for="datan">Data nasterii(dd/mm/yyyy):</label> 
			<br> <input type = "text" name = "datan"> <br> <br>
			<input type = "submit" value = "Introdu artist" />
		</form>
		
		<br>

		<form action = "MainPage.jsp" method = "GET">
			<input type = "submit" value = "Inapoi" />
		</form>

	</body>

</html>

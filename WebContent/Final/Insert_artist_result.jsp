<%@ page import="bazaDate.DataCheck, bazaDate.Artist, bazaDate.ArtistManager,bazaDate.Account, bazaDate.AccountManager" %>
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
		body {
    		text-align: center;
    		background-color: lightcyan;
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
			String nume = request.getParameter("artist");
			String trupa = request.getParameter("trupa");
			int an = (!request.getParameter("an").equals("")) ? Integer.parseInt(request.getParameter("an")) : 0;
			String nationalitate = request.getParameter("nationalitate");
			String datan = request.getParameter("datan");
			
			boolean isCorrect = DataCheck.checkArtist(nume, trupa, an, nationalitate, datan);
			
			if(!isCorrect) {
				out.println("<h3>Artistul nu a putut fi inserat. Verificati datele din casete si incercati din nou.</h3>");
				out.println("<h3>Atentie! Cititi cu atentie instructiunile din caseta si introduceti data nasterii in formatul corect.</h3>");
			} else {
				Artist artist = null;
				if(trupa.equals("") && datan.equals("")) {
					artist = new Artist(0, nume, an, nationalitate);
				} else if(trupa.equals("")) {
					artist = new Artist(0, nume, an, nationalitate, DataCheck.convertStringToLocalDate(datan, DataCheck.ART));
				} else {
					artist = new Artist(0, nume, trupa, an, nationalitate, DataCheck.convertStringToLocalDate(datan, DataCheck.ART));
				}
				ArtistManager man = new ArtistManager();
				
				System.out.println(artist);
				int newKey = man.addArtistInDB(artist);
				
				if(newKey != 0)
					out.println("<h3>Artistul a fost adaugat cu succes!</h3>");
				else
					out.println("<h3>Artistul nu a putut fi inserat. Verificati datele din casete si incercati din nou.</h3>");
			}
		%>

		<br>
		<form action = "MainPage.jsp" method = "GET">
			<input type = "submit" value = "Inapoi" />
		</form>

	</body>

</html>
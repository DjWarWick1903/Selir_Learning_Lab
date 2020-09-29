<%@ page import="bazaDate.ArtistManager,bazaDate.Account, bazaDate.AccountManager" %>
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
		<hr>
		
		<%
			String id = request.getParameter("id_artist");
			ArtistManager man = new ArtistManager();
												
			if(!id.equals("")) {						
				boolean deleted = man.deleteByID(Integer.parseInt(id));
				out.println(deleted ? "<h3>Artistul a fost sters.</h3>" : "<h3>Artistul nu a putut fi sters</h3>");
			} else {
				out.println("<h3>Introduceti mai intai un id valid in campul ID artist.</h3>");
			}
		%>

		<br>
		<form action = "MainPage.jsp" method = "GET">
			<input type = "submit" value = "Return" />
		</form>

	</body>

</html>
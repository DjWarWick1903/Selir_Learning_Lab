<%@ page import="bazaDate.ArtistManager, bazaDate.Artist,bazaDate.Account, bazaDate.AccountManager" %>
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
		
		#artisti {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  			border-collapse: collapse;
  			width: 50%;
  			margin-left: auto;
  			margin-right: 25%;
		}
		
		#artisti td, #artisti th {
			border: 1px solid #ddd;
			padding: 8px;
			text-align: center;
		}
		
		#artisti tr:nth-child(even) { background-color: #C5FFCC;}
		
		#artisti tr:nth-child(odd) { background-color: white;}
		
		#artisti tr:hover {background-color: #ddd;}
		
		#artisti th {
			padding-top: 12px;
  			padding-bottom: 12px;
 	 		text-align: center;
  			background-color: #4CAF50;
  			color: white;
  			font-size: 160%;
		}
	
		body {
    		text-align: center;
    		background-color: lightcyan;
		}
	
		form {
    		display: inline-block;
		}
		
		h1 {
			color: #4CAF50;
			font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
		}	
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
		}
		
		input {
			display: inline-block;
		}
		
		input[type=text] {
  			width: 50%;
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
		<h1>- Stergere artist -</h1>
		<br>
		
		<form action = "Delete_artist_result.jsp" method = "GET">		
			<input type = "text" name = "id_artist" placeholder="ID artist">
			<input type = "submit" value = "Sterge artist" />
		</form>
		
		<table id="artisti" >
			<tr>
			<th>ID</th>
				<th>Nume</th>
				<th>Trupa</th>
			</tr>
			
			<%	
				ArtistManager man = new ArtistManager();
				LinkedList<String> list = null;
		
				list = man.getAllArtistNames();
									

				if(list == null || list.isEmpty()) {
					out.println("<tr> <td> Inexistent </td> <td> Inexistent </td> </tr>");
				} else {
					for(String numeArtist : list) {
						Artist artist = man.getArtistInstanceByName(numeArtist);
						out.println("<tr> <td> " + artist.getId() + "</td> <td> " + artist.getNume() + " </td> <td> " + artist.getTrupa() + " </td> </tr>");
					}
				}				
			%>
		
		</table>

		<br>
		
		<form action = "MainPage.jsp" method = "GET">
			<input type = "submit" value = "Inapoi" />
		</form>

	</body>

</html>
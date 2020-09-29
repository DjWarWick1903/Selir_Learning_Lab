
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
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
		}
	</style>
	
	<script> 
		window.onload = function() {
			session.invalidate();
	    	response.sendRedirect("MainPage.jsp"); 
	    }
		
// 		var myVar;
		
// 		function myFunction() {
// 			myVar = setTimeout(redirect, 3000);
// 		}
		
// 		function redirect() {
// 			alert("Hello"); 
// 			session.invalidate(); 
// 			response.sendRedirect("MainPage.jsp"); 
// 		}

// 		window.onload = function() {
// 			sleep(2000);
// 			redirect();
// 		}
	</script>
	
	<body>
		<h1 style="color:green;">Totally not Spotify</h1>
		<hr> <br>

		Ati fost delogat. Veti fi redirectionat catre pagina principala.
		
		<%
			session.invalidate();
    		response.sendRedirect("MainPage.jsp"); 
		%>
 		
	</body>

</html>

<%@ page import="bazaDate.Account, bazaDate.AccountManager, bazaDate.SongManager, bazaDate.Song" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<html>

	<head>
		<title>Totally not Spotify</title>
	</head>
	
	<script> 
		function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("melodii");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
	</script>

	<style>
		#melodii {
			font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  			border-collapse: collapse;
  			width: 50%;
  			margin-left: auto;
  			margin-right: 25%;
		}
		
		#melodii td, #melodii th {
			border: 1px solid #ddd;
			padding: 8px;
			text-align: center;
		}
		
		#melodii tr:nth-child(even) { background-color: #C5FFCC;}
		
		#melodii tr:nth-child(odd) { background-color: white;}
		
		#melodii tr:hover {background-color: #ddd;}
		
		#melodii th {
			padding-top: 12px;
  			padding-bottom: 12px;
 	 		text-align: center;
  			background-color: #4CAF50;
  			color: white;
  			font-size: 160%;
		}

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
			font-size: 130%;
		}
		
		hr { 
			border-top: 10px solid #4CAF50;
			border-radius: 5px;
		}	
		
		input[type=submit] {
  			background-color: #4CAF50;
  			border: none;
  			color: white;
  			padding: 10px 32px;
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
		<h1>- Melodii -</h1>
		
		<table id="melodii">
		<thead>
			<tr>
				<th onclick="sortTable(0)">Nume</th>
				<th onclick="sortTable(1)">Durata</th>
			</tr>
		</thead>
		<tbody>

		<%
			String nume = request.getParameter("melodie");
			SongManager man = new SongManager();
			LinkedList<Song> list = null;

			if(nume.equals("")) {
				list = man.getAllSongs();
			} else {
				list = man.getSongsLikeName(nume);
			}

			if(list == null || list.isEmpty()) {
				out.println("<tr> <td> Inexistent </td> <td> Inexistent </td> </tr>");
			} else {
				for(Song song : list) {
					String string = song.getDurata().toString();
					String[] array = string.split(":");
					out.println("<tr> <td> " + song.getNume() + " </td> <td> " + array[1] + ":" + array[2] + " </td> </tr>");
				}
			}
		%>
		
		</tbody>
		</table>
		
		<br>
		<form action = "MainPage.jsp" method = "GET">
			<input type = "submit" value = "Inapoi" />
		</form>

	</body>

</html>

<%@ page import="bazaDate.ArtistManager, bazaDate.Artist, bazaDate.Account, bazaDate.AccountManager" %>

<%
	Account account = (Account)session.getAttribute("cont");
	AccountManager accMan = new AccountManager();
		
	if(account == null || accMan.getAccountPriviledge(account) == 0) {
		    session.invalidate();
		    response.sendRedirect("MainPage.jsp"); 
    }
%>
package bazaDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

public class DataCheck {

	public static final String ECT = "Europe/Paris";
	public static final String ART = "Africa/Cairo";

	//
	public static boolean checkArtist(String nume, String trupa, int an, String nationalitate, String datan) {

		if (nume != null && !nume.equals("") && nume.length() <= 30) {
			if (nume.contains("<script>"))
				return false;
		} else {
			return false;
		}
		
		if (trupa != null) {
			if (trupa.length() <= 30) {
				if (trupa.contains("<script>"))
					return false;
			} else {
				return false;
			}
		}

		if (an < 1900 && an > LocalDate.now().getYear()) {
			return false;
		}

		if (nationalitate != null && !nationalitate.equals("") && nationalitate.length() <= 45) {
			if (nume.contains("<script>")) {
				return false;
			}
		} else {
			return false;
		}

		if (datan != null) {
			if (datan.length() == 10) {
				DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	sdf.setLenient(false);
		    	try {
		    		sdf.parse(datan);
		    	} catch (ParseException e) {
		    		e.printStackTrace();
		    		return false;
		    	}
			}
		}

		return true;
	}
	
	public static int checkAccount(String user, String pass) {
		AccountManager man = new AccountManager();
		Account account = new Account(0, user, pass);
		int existsId = 0;
		
		if(user.length() > 30 || pass.length() > 30) {
			existsId = -1;
			return -1;
		} else {
			existsId = man.checkAccountExists(account);
		}
		
		return existsId;
	}

	public static LocalDate convertStringToLocalDate(String data, String timeZone) {
		if(data != null) {
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Instant local = date.toInstant();
			ZoneId zoneId = ZoneId.of(timeZone);
			ZonedDateTime zdt = ZonedDateTime.ofInstant(local, zoneId);
			LocalDate localDate = zdt.toLocalDate();
			return localDate;
		} else {
			return null;
		}
	}
}

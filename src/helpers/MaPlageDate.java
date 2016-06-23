package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.spi.TimeZoneNameProvider;

import Gestion_acces.structPlage;

public class MaPlageDate {

	private structPlage structPlage;

	public MaPlageDate() {
		this.structPlage = new structPlage();
	}

	public MaPlageDate(String jourDeb, String jourFin, float heureDeb, float heureFin) {
		this.structPlage = new structPlage(jourDeb, jourFin, heureDeb, heureFin);
	}

	public String toString() {
		return "Début : " + this.structPlage.jourDeb + " " + this.structPlage.heureDeb + "\n" + 
				"Fin : " + this.structPlage.jourFin + " " + this.structPlage.heureFin;
	}

	public boolean contient (GregorianCalendar gc) {
		boolean contient = false;

		if (structPlage.jourDeb.isEmpty() || structPlage.jourFin.isEmpty()) {// Personnel permanent
			//Conversion du calendar en heure-centieme
			int min =gc.get(Calendar.HOUR_OF_DAY);
			float centi = (float)gc.get(Calendar.MINUTE)/(float)60;
			float timestamp = (float)min+centi;

			if(this.structPlage.heureDeb<timestamp && this.structPlage.heureFin>timestamp ){
				return true;
			}
			else return false;

		}
		else {
			String[] dateDeb = structPlage.jourDeb.split("/");
			String[] dateFin = structPlage.jourFin.split("/");

			Calendar calDeb = new GregorianCalendar(Integer.parseInt(dateDeb[2]) + 2000, Integer.parseInt(dateDeb[1]) - 1, 
					Integer.parseInt(dateDeb[0]), (int)structPlage.heureDeb, (int)((structPlage.heureDeb-(int)structPlage.heureDeb)*60));
			Calendar calFin = new GregorianCalendar(Integer.parseInt(dateFin[2]) + 2000, Integer.parseInt(dateFin[1]) - 1, 
					Integer.parseInt(dateFin[0]), (int)structPlage.heureFin, (int)((structPlage.heureFin-(int)structPlage.heureFin)*60));

			contient = (gc.after(calDeb) && gc.before(calFin));
		}

		return contient;    	
	}

	public static GregorianCalendar stringToDateTime(String s) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		Date date = df.parse(s);
		GregorianCalendar cal =  new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}

	public static String gregorianToString(GregorianCalendar a){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		java.util.Date dateDate = a.getTime();
		String retour = dateFormat.format(dateDate);
		return retour;
	}

}

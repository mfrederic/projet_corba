package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Gestion_acces.structPlage;

public class MaPlageDate {

	private structPlage structPlage;
	
	public MaPlageDate() {
		this.structPlage = new structPlage();
	}
	
	public MaPlageDate(structPlage sP) {
		this.structPlage = sP;
	}
	
	public MaPlageDate(String jourDeb, String jourFin, float heureDeb, float heureFin) {
		this.structPlage = new structPlage(jourDeb, jourFin, heureDeb, heureFin);
	}
	
	public MaPlageDate(String jourDeb, String jourFin, String heureDeb, String heureFin) {
		this.structPlage = verifierStructPlage(jourDeb, jourFin, heureDeb, heureFin);
	}
	
	public String toString() {
		return "Début : " + this.structPlage.jourDeb + " " + this.structPlage.jourDeb + "\n" + 
				"Fin : " + this.structPlage.jourFin + " " + this.structPlage.jourFin;
	}
    
    public boolean contient (Calendar gc) {
    	boolean contient = false;
   	
    	String[] dateDeb = structPlage.jourDeb.split("-");
    	String[] dateFin = structPlage.jourFin.split("-");
    	
    	Calendar calDeb = new GregorianCalendar(Integer.parseInt(dateDeb[2]), Integer.parseInt(dateDeb[1]), 
    			Integer.parseInt(dateDeb[0]), (int)structPlage.heureDeb, (int)(structPlage.heureDeb-(int)structPlage.heureDeb)*60);
    	Calendar calFin = new GregorianCalendar(Integer.parseInt(dateFin[2]), Integer.parseInt(dateFin[1]), 
    			Integer.parseInt(dateFin[0]), (int)structPlage.heureFin, (int)(structPlage.heureFin-(int)structPlage.heureFin)*60);

    	if (gc.after(calDeb) && gc.before(calFin))
    		contient = true;
    	
		return contient;    	
    }
    
    private structPlage verifierStructPlage(String jDeb, String jFin, String hDeb, String hFin) {

		structPlage retour = null;
		boolean structOk = true;
		Float heureDeb = (float)0; 
		Float heureFin = (float)0;
		
		// Tests heures
		try {
			heureDeb = Float.parseFloat(hDeb);
			heureFin = Float.parseFloat(hFin);
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			structOk = false;
		}
		
		// Tests jours
		Date dateDeb = null;
		Date dateFin = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
            dateDeb = sdf.parse(jDeb);
            dateFin = sdf.parse(jFin);
            if (!jDeb.equals(sdf.format(dateDeb)) || !jFin.equals(sdf.format(dateFin))) {
                structOk = false;
            }
        } catch (ParseException ex) {
            System.out.println(ex.toString());
            structOk = false;
        }
		
        if (structOk)
        	retour = new structPlage(jDeb, jFin, heureDeb, heureFin);

		return retour;
	}
	
}

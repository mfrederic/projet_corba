package helpers;

import java.util.Calendar;
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
}

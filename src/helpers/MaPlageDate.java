package helpers;

import java.util.GregorianCalendar;

import Gestion_acces.structPlage;

public class MaPlageDate {

	private structPlage structPlage;
	
	public MaPlageDate() {
		this.structPlage = new structPlage();
	}
	
	public MaPlageDate(String jourDeb, String jourFin, float heureDeb, float heureFin) {
		this.structPlage = new structPlage(jourDeb, jourFin, heureDeb, heureFin);
	}
	
    
    public boolean contient (GregorianCalendar gc) {
    	boolean contient = false;
   	
    	String[] dateDeb = structPlage.jourDeb.split("-");
    	String[] dateFin = structPlage.jourFin.split("-");
    	
    	GregorianCalendar calDeb = new GregorianCalendar(2000+Integer.parseInt(dateDeb[2]), Integer.parseInt(dateDeb[1]), 
    			Integer.parseInt(dateDeb[0]), (int)structPlage.heureDeb, (int)(structPlage.heureDeb-(int)structPlage.heureDeb)*60);
    	GregorianCalendar calFin = new GregorianCalendar(2000+Integer.parseInt(dateFin[2]), Integer.parseInt(dateFin[1]), 
    			Integer.parseInt(dateFin[0]), (int)structPlage.heureFin, (int)(structPlage.heureFin-(int)structPlage.heureFin)*60);

    	
    	if (gc.after(calDeb) && gc.before(calFin))
    		contient = true;
    	
		return contient;    	
    }
}

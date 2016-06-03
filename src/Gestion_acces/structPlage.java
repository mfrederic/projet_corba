package Gestion_acces;

import java.util.GregorianCalendar;

/**
 * Struct definition : structPlage
 * 
 * @author OpenORB Compiler
*/
public final class structPlage implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member jourDeb
     */
    public String jourDeb;

    /**
     * Struct member jourFin
     */
    public String jourFin;

    /**
     * Struct member heureDeb
     */
    public float heureDeb;

    /**
     * Struct member heureFin
     */
    public float heureFin;

    /**
     * Default constructor
     */
    public structPlage()
    { }

    /**
     * Constructor with fields initialization
     * @param jourDeb jourDeb struct member
     * @param jourFin jourFin struct member
     * @param heureDeb heureDeb struct member
     * @param heureFin heureFin struct member
     */
    public structPlage(String jourDeb, String jourFin, float heureDeb, float heureFin)
    {
        this.jourDeb = jourDeb;
        this.jourFin = jourFin;
        this.heureDeb = heureDeb;
        this.heureFin = heureFin;
    }
    
    public boolean contient (GregorianCalendar gc) {
    	boolean contient = false;
   	
    	String[] dateDeb = jourDeb.split("-");
    	String[] dateFin = jourFin.split("-");
    	
    	GregorianCalendar calDeb = new GregorianCalendar(2000+Integer.parseInt(dateDeb[2]), Integer.parseInt(dateDeb[1]), 
    			Integer.parseInt(dateDeb[0]), (int)heureDeb, (int)(heureDeb-(int)heureDeb)*60);
    	GregorianCalendar calFin = new GregorianCalendar(2000+Integer.parseInt(dateFin[2]), Integer.parseInt(dateFin[1]), 
    			Integer.parseInt(dateFin[0]), (int)heureFin, (int)(heureFin-(int)heureFin)*60);

    	
    	if (gc.after(calDeb) && gc.before(calFin))
    		contient = true;
    	
		return contient;    	
    }

}

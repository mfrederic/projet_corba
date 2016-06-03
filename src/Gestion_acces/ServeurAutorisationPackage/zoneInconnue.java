package Gestion_acces.ServeurAutorisationPackage;

/**
 * Exception definition : zoneInconnue
 * 
 * @author OpenORB Compiler
 */
public final class zoneInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member zone
     */
    public short zone;

    /**
     * Default constructor
     */
    public zoneInconnue()
    {
        super(zoneInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param zone zone exception member
     */
    public zoneInconnue(short zone)
    {
        super(zoneInconnueHelper.id());
        this.zone = zone;
    }

    /**
     * Full constructor with fields initialization
     * @param zone zone exception member
     */
    public zoneInconnue(String orb_reason, short zone)
    {
        super(zoneInconnueHelper.id() +" " +  orb_reason);
        this.zone = zone;
    }

    public String toString() {
    	return "Zone inconnue (id = " + this.zone + ")";
    }
    
}

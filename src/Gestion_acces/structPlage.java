package Gestion_acces;

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

}

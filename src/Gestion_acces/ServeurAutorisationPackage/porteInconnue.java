package Gestion_acces.ServeurAutorisationPackage;

/**
 * Exception definition : porteInconnue
 * 
 * @author OpenORB Compiler
 */
public final class porteInconnue extends org.omg.CORBA.UserException
{
    /**
     * Exception member idPorte
     */
    public short idPorte;

    /**
     * Default constructor
     */
    public porteInconnue()
    {
        super(porteInconnueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param idPorte idPorte exception member
     */
    public porteInconnue(short idPorte)
    {
        super(porteInconnueHelper.id());
        this.idPorte = idPorte;
    }

    /**
     * Full constructor with fields initialization
     * @param idPorte idPorte exception member
     */
    public porteInconnue(String orb_reason, short idPorte)
    {
        super(porteInconnueHelper.id() +" " +  orb_reason);
        this.idPorte = idPorte;
    }

}

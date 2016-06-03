package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : compteDejaCree
 * 
 * @author OpenORB Compiler
 */
public final class compteDejaCree extends org.omg.CORBA.UserException
{
    /**
     * Exception member nom
     */
    public String nom;

    /**
     * Exception member prenom
     */
    public String prenom;

    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public compteDejaCree()
    {
        super(compteDejaCreeHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param nom nom exception member
     * @param prenom prenom exception member
     * @param id id exception member
     */
    public compteDejaCree(String nom, String prenom, short id)
    {
        super(compteDejaCreeHelper.id());
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param nom nom exception member
     * @param prenom prenom exception member
     * @param id id exception member
     */
    public compteDejaCree(String orb_reason, String nom, String prenom, short id)
    {
        super(compteDejaCreeHelper.id() +" " +  orb_reason);
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

}

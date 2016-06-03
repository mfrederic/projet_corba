package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : compteInexistant
 * 
 * @author OpenORB Compiler
 */
public final class compteInexistant extends org.omg.CORBA.UserException
{
    /**
     * Exception member user
     */
    public String user;

    /**
     * Default constructor
     */
    public compteInexistant()
    {
        super(compteInexistantHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param user user exception member
     */
    public compteInexistant(String user)
    {
        super(compteInexistantHelper.id());
        this.user = user;
    }

    /**
     * Full constructor with fields initialization
     * @param user user exception member
     */
    public compteInexistant(String orb_reason, String user)
    {
        super(compteInexistantHelper.id() +" " +  orb_reason);
        this.user = user;
    }

}

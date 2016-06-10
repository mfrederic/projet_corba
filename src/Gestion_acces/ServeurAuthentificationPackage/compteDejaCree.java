package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : compteDejaCree
 * 
 * @author OpenORB Compiler
 */
public final class compteDejaCree extends org.omg.CORBA.UserException
{
    /**
     * Exception member user
     */
    public String user;

    /**
     * Default constructor
     */
    public compteDejaCree()
    {
        super(compteDejaCreeHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param user user exception member
     */
    public compteDejaCree(String user)
    {
        super(compteDejaCreeHelper.id());
        this.user = user;
    }

    /**
     * Full constructor with fields initialization
     * @param user user exception member
     */
    public compteDejaCree(String orb_reason, String user)
    {
        super(compteDejaCreeHelper.id() +" " +  orb_reason);
        this.user = user;
    }

}

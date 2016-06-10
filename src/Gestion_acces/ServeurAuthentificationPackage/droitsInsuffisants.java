package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : droitsInsuffisants
 * 
 * @author OpenORB Compiler
 */
public final class droitsInsuffisants extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public droitsInsuffisants()
    {
        super(droitsInsuffisantsHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public droitsInsuffisants(String raison)
    {
        super(droitsInsuffisantsHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public droitsInsuffisants(String orb_reason, String raison)
    {
        super(droitsInsuffisantsHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}

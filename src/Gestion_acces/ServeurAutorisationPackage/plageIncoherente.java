package Gestion_acces.ServeurAutorisationPackage;

/**
 * Exception definition : plageIncoherente
 * 
 * @author OpenORB Compiler
 */
public final class plageIncoherente extends org.omg.CORBA.UserException
{
    /**
     * Exception member raison
     */
    public String raison;

    /**
     * Default constructor
     */
    public plageIncoherente()
    {
        super(plageIncoherenteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param raison raison exception member
     */
    public plageIncoherente(String raison)
    {
        super(plageIncoherenteHelper.id());
        this.raison = raison;
    }

    /**
     * Full constructor with fields initialization
     * @param raison raison exception member
     */
    public plageIncoherente(String orb_reason, String raison)
    {
        super(plageIncoherenteHelper.id() +" " +  orb_reason);
        this.raison = raison;
    }

}

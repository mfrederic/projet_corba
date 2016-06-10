package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : suppressionInterdite
 * 
 * @author OpenORB Compiler
 */
public final class suppressionInterdite extends org.omg.CORBA.UserException
{
    /**
     * Exception member role
     */
    public Gestion_acces.rolePersonne role;

    /**
     * Default constructor
     */
    public suppressionInterdite()
    {
        super(suppressionInterditeHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param role role exception member
     */
    public suppressionInterdite(Gestion_acces.rolePersonne role)
    {
        super(suppressionInterditeHelper.id());
        this.role = role;
    }

    /**
     * Full constructor with fields initialization
     * @param role role exception member
     */
    public suppressionInterdite(String orb_reason, Gestion_acces.rolePersonne role)
    {
        super(suppressionInterditeHelper.id() +" " +  orb_reason);
        this.role = role;
    }

}

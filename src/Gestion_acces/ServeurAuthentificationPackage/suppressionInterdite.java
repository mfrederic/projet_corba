package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : suppressionInterdite
 * 
 * @author OpenORB Compiler
 */
public final class suppressionInterdite extends org.omg.CORBA.UserException
{
    /**
     * Exception member statut
     */
    public Gestion_acces.statutPersonne statut;

    /**
     * Default constructor
     */
    public suppressionInterdite()
    {
        super(suppressionInterditeHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param statut statut exception member
     */
    public suppressionInterdite(Gestion_acces.statutPersonne statut)
    {
        super(suppressionInterditeHelper.id());
        this.statut = statut;
    }

    /**
     * Full constructor with fields initialization
     * @param statut statut exception member
     */
    public suppressionInterdite(String orb_reason, Gestion_acces.statutPersonne statut)
    {
        super(suppressionInterditeHelper.id() +" " +  orb_reason);
        this.statut = statut;
    }

}

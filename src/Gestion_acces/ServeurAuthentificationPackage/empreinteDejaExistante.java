package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Exception definition : empreinteDejaExistante
 * 
 * @author OpenORB Compiler
 */
public final class empreinteDejaExistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member emp
     */
    public String emp;

    /**
     * Default constructor
     */
    public empreinteDejaExistante()
    {
        super(empreinteDejaExistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param emp emp exception member
     */
    public empreinteDejaExistante(String emp)
    {
        super(empreinteDejaExistanteHelper.id());
        this.emp = emp;
    }

    /**
     * Full constructor with fields initialization
     * @param emp emp exception member
     */
    public empreinteDejaExistante(String orb_reason, String emp)
    {
        super(empreinteDejaExistanteHelper.id() +" " +  orb_reason);
        this.emp = emp;
    }

}

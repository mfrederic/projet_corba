package Gestion_acces.ServeurAutorisationPackage;

/**
 * Exception definition : autorisationInexistante
 * 
 * @author OpenORB Compiler
 */
public final class autorisationInexistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member idAutorisation
     */
    public short idAutorisation;

    /**
     * Default constructor
     */
    public autorisationInexistante()
    {
        super(autorisationInexistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param idAutorisation idAutorisation exception member
     */
    public autorisationInexistante(short idAutorisation)
    {
        super(autorisationInexistanteHelper.id());
        this.idAutorisation = idAutorisation;
    }

    /**
     * Full constructor with fields initialization
     * @param idAutorisation idAutorisation exception member
     */
    public autorisationInexistante(String orb_reason, short idAutorisation)
    {
        super(autorisationInexistanteHelper.id() +" " +  orb_reason);
        this.idAutorisation = idAutorisation;
    }
    
    public String toString() {
    	return "Aucune autorisation correspondante trouvée (id = " + this.idAutorisation + ")";
    }

}

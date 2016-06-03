package Gestion_acces;

/**
 * Exception definition : personneInexistante
 * 
 * @author OpenORB Compiler
 */
public final class personneInexistante extends org.omg.CORBA.UserException
{
    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public personneInexistante()
    {
        super(personneInexistanteHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param id id exception member
     */
    public personneInexistante(short id)
    {
        super(personneInexistanteHelper.id());
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param id id exception member
     */
    public personneInexistante(String orb_reason, short id)
    {
        super(personneInexistanteHelper.id() +" " +  orb_reason);
        this.id = id;
    }
    
    public String toString() {
    	return "Personne inexistante dans la base (id = " + this.id + ")";
    }

}

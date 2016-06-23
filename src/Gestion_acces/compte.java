package Gestion_acces;

/**
 * Struct definition : compte
 * 
 * @author OpenORB Compiler
*/
public final class compte implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member user
     */
    public String user;

    /**
     * Struct member password
     */
    public String password;

    /**
     * Struct member empreinte
     */
    public String empreinte;

    /**
     * Struct member refPersonne
     */
    public short refPersonne;

    /**
     * Default constructor
     */
    public compte()
    { }

    /**
     * Constructor with fields initialization
     * @param user user struct member
     * @param password password struct member
     * @param empreinte empreinte struct member
     * @param refPersonne refPersonne struct member
     */
    public compte(String user, String password, String empreinte, short refPersonne)
    {
        this.user = user;
        this.password = password;
        this.empreinte = empreinte;
        this.refPersonne = refPersonne;
    }

}

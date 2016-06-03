package Gestion_acces;

/**
 * Struct definition : personne
 * 
 * @author OpenORB Compiler
*/
public final class personne implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member idPers
     */
    public short idPers;

    /**
     * Struct member nom
     */
    public String nom;

    /**
     * Struct member prenom
     */
    public String prenom;

    /**
     * Struct member ph
     */
    public String ph;

    /**
     * Struct member statut
     */
    public Gestion_acces.statutPersonne statut;

    /**
     * Struct member role
     */
    public Gestion_acces.rolePersonne role;

    /**
     * Default constructor
     */
    public personne()
    { }

    /**
     * Constructor with fields initialization
     * @param idPers idPers struct member
     * @param nom nom struct member
     * @param prenom prenom struct member
     * @param ph ph struct member
     * @param statut statut struct member
     * @param role role struct member
     */
    public personne(short idPers, String nom, String prenom, String ph, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
    {
        this.idPers = idPers;
        this.nom = nom;
        this.prenom = prenom;
        this.ph = ph;
        this.statut = statut;
        this.role = role;
    }

}

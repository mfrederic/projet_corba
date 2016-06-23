package Gestion_acces;

/**
 * Struct definition : log
 * 
 * @author OpenORB Compiler
*/
public final class log implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member idJournal
     */
    public short idJournal;

    /**
     * Struct member timestamp
     */
    public String timestamp;

    /**
     * Struct member typeAcces
     */
    public String typeAcces;

    /**
     * Struct member refPersonne
     */
    public short refPersonne;

    /**
     * Struct member resultat
     */
    public boolean resultat;

    /**
     * Struct member commentaire
     */
    public String commentaire;

    /**
     * Default constructor
     */
    public log()
    { }

    /**
     * Constructor with fields initialization
     * @param idJournal idJournal struct member
     * @param timestamp timestamp struct member
     * @param typeAcces typeAcces struct member
     * @param refPersonne refPersonne struct member
     * @param resultat resultat struct member
     * @param commentaire commentaire struct member
     */
    public log(short idJournal, String timestamp, String typeAcces, short refPersonne, boolean resultat, String commentaire)
    {
        this.idJournal = idJournal;
        this.timestamp = timestamp;
        this.typeAcces = typeAcces;
        this.refPersonne = refPersonne;
        this.resultat = resultat;
        this.commentaire = commentaire;
    }

}

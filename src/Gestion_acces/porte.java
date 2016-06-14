package Gestion_acces;

/**
 * Struct definition : porte
 * 
 * @author OpenORB Compiler
*/
public final class porte implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member idPorte
     */
    public short idPorte;

    /**
     * Struct member libellePorte
     */
    public String libellePorte;

    /**
     * Struct member refZone
     */
    public short refZone;

    /**
     * Default constructor
     */
    public porte()
    { }

    /**
     * Constructor with fields initialization
     * @param idPorte idPorte struct member
     * @param libellePorte libellePorte struct member
     * @param refZone refZone struct member
     */
    public porte(short idPorte, String libellePorte, short refZone)
    {
        this.idPorte = idPorte;
        this.libellePorte = libellePorte;
        this.refZone = refZone;
    }

}

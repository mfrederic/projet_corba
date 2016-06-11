package Gestion_acces;

/**
 * Struct definition : autorisation
 * 
 * @author OpenORB Compiler
*/
public final class autorisation implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member numAuto
     */
    public short numAuto;

    /**
     * Struct member refPers
     */
    public short refPers;

    /**
     * Struct member sP
     */
    public Gestion_acces.structPlage sP;

    /**
     * Struct member refZone
     */
    public short refZone;

    /**
     * Default constructor
     */
    public autorisation()
    { }

    /**
     * Constructor with fields initialization
     * @param numAuto numAuto struct member
     * @param refPers refPers struct member
     * @param sP sP struct member
     * @param refZone refZone struct member
     */
    public autorisation(short numAuto, short refPers, Gestion_acces.structPlage sP, short refZone)
    {
        this.numAuto = numAuto;
        this.refPers = refPers;
        this.sP = sP;
        this.refZone = refZone;
    }

}

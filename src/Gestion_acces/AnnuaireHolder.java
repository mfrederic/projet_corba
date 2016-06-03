package Gestion_acces;

/**
 * Holder class for : Annuaire
 * 
 * @author OpenORB Compiler
 */
final public class AnnuaireHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Annuaire value
     */
    public Gestion_acces.Annuaire value;

    /**
     * Default constructor
     */
    public AnnuaireHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AnnuaireHolder(Gestion_acces.Annuaire initial)
    {
        value = initial;
    }

    /**
     * Read Annuaire from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AnnuaireHelper.read(istream);
    }

    /**
     * Write Annuaire into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AnnuaireHelper.write(ostream,value);
    }

    /**
     * Return the Annuaire TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AnnuaireHelper.type();
    }

}

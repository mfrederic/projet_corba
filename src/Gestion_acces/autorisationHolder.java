package Gestion_acces;

/**
 * Holder class for : autorisation
 * 
 * @author OpenORB Compiler
 */
final public class autorisationHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal autorisation value
     */
    public Gestion_acces.autorisation value;

    /**
     * Default constructor
     */
    public autorisationHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public autorisationHolder(Gestion_acces.autorisation initial)
    {
        value = initial;
    }

    /**
     * Read autorisation from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = autorisationHelper.read(istream);
    }

    /**
     * Write autorisation into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        autorisationHelper.write(ostream,value);
    }

    /**
     * Return the autorisation TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return autorisationHelper.type();
    }

}

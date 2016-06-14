package Gestion_acces;

/**
 * Holder class for : porte
 * 
 * @author OpenORB Compiler
 */
final public class porteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal porte value
     */
    public Gestion_acces.porte value;

    /**
     * Default constructor
     */
    public porteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public porteHolder(Gestion_acces.porte initial)
    {
        value = initial;
    }

    /**
     * Read porte from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = porteHelper.read(istream);
    }

    /**
     * Write porte into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        porteHelper.write(ostream,value);
    }

    /**
     * Return the porte TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return porteHelper.type();
    }

}

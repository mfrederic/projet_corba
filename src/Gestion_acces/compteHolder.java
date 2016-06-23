package Gestion_acces;

/**
 * Holder class for : compte
 * 
 * @author OpenORB Compiler
 */
final public class compteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal compte value
     */
    public Gestion_acces.compte value;

    /**
     * Default constructor
     */
    public compteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public compteHolder(Gestion_acces.compte initial)
    {
        value = initial;
    }

    /**
     * Read compte from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = compteHelper.read(istream);
    }

    /**
     * Write compte into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        compteHelper.write(ostream,value);
    }

    /**
     * Return the compte TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return compteHelper.type();
    }

}

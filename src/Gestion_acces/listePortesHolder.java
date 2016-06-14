package Gestion_acces;

/**
 * Holder class for : listePortes
 * 
 * @author OpenORB Compiler
 */
final public class listePortesHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listePortes value
     */
    public Gestion_acces.porte[] value;

    /**
     * Default constructor
     */
    public listePortesHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listePortesHolder(Gestion_acces.porte[] initial)
    {
        value = initial;
    }

    /**
     * Read listePortes from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listePortesHelper.read(istream);
    }

    /**
     * Write listePortes into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listePortesHelper.write(ostream,value);
    }

    /**
     * Return the listePortes TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listePortesHelper.type();
    }

}

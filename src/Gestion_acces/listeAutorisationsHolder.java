package Gestion_acces;

/**
 * Holder class for : listeAutorisations
 * 
 * @author OpenORB Compiler
 */
final public class listeAutorisationsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listeAutorisations value
     */
    public Gestion_acces.autorisation[] value;

    /**
     * Default constructor
     */
    public listeAutorisationsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listeAutorisationsHolder(Gestion_acces.autorisation[] initial)
    {
        value = initial;
    }

    /**
     * Read listeAutorisations from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listeAutorisationsHelper.read(istream);
    }

    /**
     * Write listeAutorisations into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listeAutorisationsHelper.write(ostream,value);
    }

    /**
     * Return the listeAutorisations TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listeAutorisationsHelper.type();
    }

}

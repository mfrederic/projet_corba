package Gestion_acces;

/**
 * Holder class for : listeComptes
 * 
 * @author OpenORB Compiler
 */
final public class listeComptesHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listeComptes value
     */
    public Gestion_acces.compte[] value;

    /**
     * Default constructor
     */
    public listeComptesHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listeComptesHolder(Gestion_acces.compte[] initial)
    {
        value = initial;
    }

    /**
     * Read listeComptes from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listeComptesHelper.read(istream);
    }

    /**
     * Write listeComptes into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listeComptesHelper.write(ostream,value);
    }

    /**
     * Return the listeComptes TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listeComptesHelper.type();
    }

}

package Gestion_acces;

/**
 * Holder class for : listeIdZones
 * 
 * @author OpenORB Compiler
 */
final public class listeIdZonesHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listeIdZones value
     */
    public short[] value;

    /**
     * Default constructor
     */
    public listeIdZonesHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listeIdZonesHolder(short[] initial)
    {
        value = initial;
    }

    /**
     * Read listeIdZones from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listeIdZonesHelper.read(istream);
    }

    /**
     * Write listeIdZones into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listeIdZonesHelper.write(ostream,value);
    }

    /**
     * Return the listeIdZones TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listeIdZonesHelper.type();
    }

}

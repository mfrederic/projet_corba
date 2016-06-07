package Gestion_acces;

/**
 * Holder class for : listeZones
 * 
 * @author OpenORB Compiler
 */
final public class listeZonesHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listeZones value
     */
    public short[] value;

    /**
     * Default constructor
     */
    public listeZonesHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listeZonesHolder(short[] initial)
    {
        value = initial;
    }

    /**
     * Read listeZones from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listeZonesHelper.read(istream);
    }

    /**
     * Write listeZones into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listeZonesHelper.write(ostream,value);
    }

    /**
     * Return the listeZones TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listeZonesHelper.type();
    }

}

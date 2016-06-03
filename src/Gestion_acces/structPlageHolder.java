package Gestion_acces;

/**
 * Holder class for : structPlage
 * 
 * @author OpenORB Compiler
 */
final public class structPlageHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal structPlage value
     */
    public Gestion_acces.structPlage value;

    /**
     * Default constructor
     */
    public structPlageHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public structPlageHolder(Gestion_acces.structPlage initial)
    {
        value = initial;
    }

    /**
     * Read structPlage from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = structPlageHelper.read(istream);
    }

    /**
     * Write structPlage into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        structPlageHelper.write(ostream,value);
    }

    /**
     * Return the structPlage TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return structPlageHelper.type();
    }

}

package Gestion_acces;

/**
 * Holder class for : ServeurAutorisation
 * 
 * @author OpenORB Compiler
 */
final public class ServeurAutorisationHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal ServeurAutorisation value
     */
    public Gestion_acces.ServeurAutorisation value;

    /**
     * Default constructor
     */
    public ServeurAutorisationHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ServeurAutorisationHolder(Gestion_acces.ServeurAutorisation initial)
    {
        value = initial;
    }

    /**
     * Read ServeurAutorisation from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ServeurAutorisationHelper.read(istream);
    }

    /**
     * Write ServeurAutorisation into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ServeurAutorisationHelper.write(ostream,value);
    }

    /**
     * Return the ServeurAutorisation TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ServeurAutorisationHelper.type();
    }

}

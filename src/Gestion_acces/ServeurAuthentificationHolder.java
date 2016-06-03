package Gestion_acces;

/**
 * Holder class for : ServeurAuthentification
 * 
 * @author OpenORB Compiler
 */
final public class ServeurAuthentificationHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal ServeurAuthentification value
     */
    public Gestion_acces.ServeurAuthentification value;

    /**
     * Default constructor
     */
    public ServeurAuthentificationHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ServeurAuthentificationHolder(Gestion_acces.ServeurAuthentification initial)
    {
        value = initial;
    }

    /**
     * Read ServeurAuthentification from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ServeurAuthentificationHelper.read(istream);
    }

    /**
     * Write ServeurAuthentification into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ServeurAuthentificationHelper.write(ostream,value);
    }

    /**
     * Return the ServeurAuthentification TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ServeurAuthentificationHelper.type();
    }

}

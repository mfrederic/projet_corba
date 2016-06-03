package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Holder class for : compteDejaCree
 * 
 * @author OpenORB Compiler
 */
final public class compteDejaCreeHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal compteDejaCree value
     */
    public Gestion_acces.ServeurAuthentificationPackage.compteDejaCree value;

    /**
     * Default constructor
     */
    public compteDejaCreeHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public compteDejaCreeHolder(Gestion_acces.ServeurAuthentificationPackage.compteDejaCree initial)
    {
        value = initial;
    }

    /**
     * Read compteDejaCree from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = compteDejaCreeHelper.read(istream);
    }

    /**
     * Write compteDejaCree into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        compteDejaCreeHelper.write(ostream,value);
    }

    /**
     * Return the compteDejaCree TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return compteDejaCreeHelper.type();
    }

}

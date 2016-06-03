package Gestion_acces;

/**
 * Holder class for : rolePersonne
 * 
 * @author OpenORB Compiler
 */
final public class rolePersonneHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal rolePersonne value
     */
    public Gestion_acces.rolePersonne value;

    /**
     * Default constructor
     */
    public rolePersonneHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public rolePersonneHolder(Gestion_acces.rolePersonne initial)
    {
        value = initial;
    }

    /**
     * Read rolePersonne from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = rolePersonneHelper.read(istream);
    }

    /**
     * Write rolePersonne into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        rolePersonneHelper.write(ostream,value);
    }

    /**
     * Return the rolePersonne TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return rolePersonneHelper.type();
    }

}

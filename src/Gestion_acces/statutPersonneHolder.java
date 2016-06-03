package Gestion_acces;

/**
 * Holder class for : statutPersonne
 * 
 * @author OpenORB Compiler
 */
final public class statutPersonneHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal statutPersonne value
     */
    public Gestion_acces.statutPersonne value;

    /**
     * Default constructor
     */
    public statutPersonneHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public statutPersonneHolder(Gestion_acces.statutPersonne initial)
    {
        value = initial;
    }

    /**
     * Read statutPersonne from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = statutPersonneHelper.read(istream);
    }

    /**
     * Write statutPersonne into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        statutPersonneHelper.write(ostream,value);
    }

    /**
     * Return the statutPersonne TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return statutPersonneHelper.type();
    }

}

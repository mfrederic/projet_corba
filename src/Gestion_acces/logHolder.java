package Gestion_acces;

/**
 * Holder class for : log
 * 
 * @author OpenORB Compiler
 */
final public class logHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal log value
     */
    public Gestion_acces.log value;

    /**
     * Default constructor
     */
    public logHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public logHolder(Gestion_acces.log initial)
    {
        value = initial;
    }

    /**
     * Read log from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = logHelper.read(istream);
    }

    /**
     * Write log into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        logHelper.write(ostream,value);
    }

    /**
     * Return the log TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return logHelper.type();
    }

}

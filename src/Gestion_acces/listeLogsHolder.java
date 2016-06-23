package Gestion_acces;

/**
 * Holder class for : listeLogs
 * 
 * @author OpenORB Compiler
 */
final public class listeLogsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listeLogs value
     */
    public Gestion_acces.log[] value;

    /**
     * Default constructor
     */
    public listeLogsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listeLogsHolder(Gestion_acces.log[] initial)
    {
        value = initial;
    }

    /**
     * Read listeLogs from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listeLogsHelper.read(istream);
    }

    /**
     * Write listeLogs into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listeLogsHelper.write(ostream,value);
    }

    /**
     * Return the listeLogs TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listeLogsHelper.type();
    }

}

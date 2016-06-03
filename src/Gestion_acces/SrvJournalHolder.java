package Gestion_acces;

/**
 * Holder class for : SrvJournal
 * 
 * @author OpenORB Compiler
 */
final public class SrvJournalHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal SrvJournal value
     */
    public Gestion_acces.SrvJournal value;

    /**
     * Default constructor
     */
    public SrvJournalHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public SrvJournalHolder(Gestion_acces.SrvJournal initial)
    {
        value = initial;
    }

    /**
     * Read SrvJournal from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = SrvJournalHelper.read(istream);
    }

    /**
     * Write SrvJournal into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        SrvJournalHelper.write(ostream,value);
    }

    /**
     * Return the SrvJournal TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return SrvJournalHelper.type();
    }

}

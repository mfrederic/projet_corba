package Gestion_acces;

/** 
 * Helper class for : SrvJournal
 *  
 * @author OpenORB Compiler
 */ 
public class SrvJournalHelper
{
    /**
     * Insert SrvJournal into an any
     * @param a an any
     * @param t SrvJournal value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.SrvJournal t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract SrvJournal from an any
     * @param a an any
     * @return the extracted SrvJournal value
     */
    public static Gestion_acces.SrvJournal extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return Gestion_acces.SrvJournalHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the SrvJournal TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"SrvJournal");
        }
        return _tc;
    }

    /**
     * Return the SrvJournal IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/SrvJournal:1.0";

    /**
     * Read SrvJournal from a marshalled stream
     * @param istream the input stream
     * @return the readed SrvJournal value
     */
    public static Gestion_acces.SrvJournal read(org.omg.CORBA.portable.InputStream istream)
    {
        return(Gestion_acces.SrvJournal)istream.read_Object(Gestion_acces._SrvJournalStub.class);
    }

    /**
     * Write SrvJournal into a marshalled stream
     * @param ostream the output stream
     * @param value SrvJournal value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.SrvJournal value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to SrvJournal
     * @param obj the CORBA Object
     * @return SrvJournal Object
     */
    public static SrvJournal narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof SrvJournal)
            return (SrvJournal)obj;

        if (obj._is_a(id()))
        {
            _SrvJournalStub stub = new _SrvJournalStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to SrvJournal
     * @param obj the CORBA Object
     * @return SrvJournal Object
     */
    public static SrvJournal unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof SrvJournal)
            return (SrvJournal)obj;

        _SrvJournalStub stub = new _SrvJournalStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}

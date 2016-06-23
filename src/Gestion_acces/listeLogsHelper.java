package Gestion_acces;

/** 
 * Helper class for : listeLogs
 *  
 * @author OpenORB Compiler
 */ 
public class listeLogsHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert listeLogs into an any
     * @param a an any
     * @param t listeLogs value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.log[] t)
    {
        a.insert_Streamable(new Gestion_acces.listeLogsHolder(t));
    }

    /**
     * Extract listeLogs from an any
     * @param a an any
     * @return the extracted listeLogs value
     */
    public static Gestion_acces.log[] extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if(HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.listeLogsHolder)
                    return ((Gestion_acces.listeLogsHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.listeLogsHolder h = new Gestion_acces.listeLogsHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the listeLogs TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"listeLogs",orb.create_sequence_tc(0,Gestion_acces.logHelper.type()));
        }
        return _tc;
    }

    /**
     * Return the listeLogs IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/listeLogs:1.0";

    /**
     * Read listeLogs from a marshalled stream
     * @param istream the input stream
     * @return the readed listeLogs value
     */
    public static Gestion_acces.log[] read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.log[] new_one;
        {
        int size7 = istream.read_ulong();
        new_one = new Gestion_acces.log[size7];
        for (int i7=0; i7<new_one.length; i7++)
         {
            new_one[i7] = Gestion_acces.logHelper.read(istream);

         }
        }

        return new_one;
    }

    /**
     * Write listeLogs into a marshalled stream
     * @param ostream the output stream
     * @param value listeLogs value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.log[] value)
    {
        ostream.write_ulong(value.length);
        for (int i7=0; i7<value.length; i7++)
        {
            Gestion_acces.logHelper.write(ostream,value[i7]);

        }
    }

}

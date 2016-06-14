package Gestion_acces;

/** 
 * Helper class for : listePortes
 *  
 * @author OpenORB Compiler
 */ 
public class listePortesHelper
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
     * Insert listePortes into an any
     * @param a an any
     * @param t listePortes value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.porte[] t)
    {
        a.insert_Streamable(new Gestion_acces.listePortesHolder(t));
    }

    /**
     * Extract listePortes from an any
     * @param a an any
     * @return the extracted listePortes value
     */
    public static Gestion_acces.porte[] extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if(HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.listePortesHolder)
                    return ((Gestion_acces.listePortesHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.listePortesHolder h = new Gestion_acces.listePortesHolder(read(a.create_input_stream()));
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
     * Return the listePortes TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"listePortes",orb.create_sequence_tc(0,Gestion_acces.porteHelper.type()));
        }
        return _tc;
    }

    /**
     * Return the listePortes IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/listePortes:1.0";

    /**
     * Read listePortes from a marshalled stream
     * @param istream the input stream
     * @return the readed listePortes value
     */
    public static Gestion_acces.porte[] read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.porte[] new_one;
        {
        int size7 = istream.read_ulong();
        new_one = new Gestion_acces.porte[size7];
        for (int i7=0; i7<new_one.length; i7++)
         {
            new_one[i7] = Gestion_acces.porteHelper.read(istream);

         }
        }

        return new_one;
    }

    /**
     * Write listePortes into a marshalled stream
     * @param ostream the output stream
     * @param value listePortes value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.porte[] value)
    {
        ostream.write_ulong(value.length);
        for (int i7=0; i7<value.length; i7++)
        {
            Gestion_acces.porteHelper.write(ostream,value[i7]);

        }
    }

}

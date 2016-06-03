package Gestion_acces;

/** 
 * Helper class for : listePersonnes
 *  
 * @author OpenORB Compiler
 */ 
public class listePersonnesHelper
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
     * Insert listePersonnes into an any
     * @param a an any
     * @param t listePersonnes value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.personne[] t)
    {
        a.insert_Streamable(new Gestion_acces.listePersonnesHolder(t));
    }

    /**
     * Extract listePersonnes from an any
     * @param a an any
     * @return the extracted listePersonnes value
     */
    public static Gestion_acces.personne[] extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if(HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.listePersonnesHolder)
                    return ((Gestion_acces.listePersonnesHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.listePersonnesHolder h = new Gestion_acces.listePersonnesHolder(read(a.create_input_stream()));
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
     * Return the listePersonnes TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"listePersonnes",orb.create_sequence_tc(0,Gestion_acces.personneHelper.type()));
        }
        return _tc;
    }

    /**
     * Return the listePersonnes IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/listePersonnes:1.0";

    /**
     * Read listePersonnes from a marshalled stream
     * @param istream the input stream
     * @return the readed listePersonnes value
     */
    public static Gestion_acces.personne[] read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.personne[] new_one;
        {
        int size7 = istream.read_ulong();
        new_one = new Gestion_acces.personne[size7];
        for (int i7=0; i7<new_one.length; i7++)
         {
            new_one[i7] = Gestion_acces.personneHelper.read(istream);

         }
        }

        return new_one;
    }

    /**
     * Write listePersonnes into a marshalled stream
     * @param ostream the output stream
     * @param value listePersonnes value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.personne[] value)
    {
        ostream.write_ulong(value.length);
        for (int i7=0; i7<value.length; i7++)
        {
            Gestion_acces.personneHelper.write(ostream,value[i7]);

        }
    }

}

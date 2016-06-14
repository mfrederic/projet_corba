package Gestion_acces;

/** 
 * Helper class for : porte
 *  
 * @author OpenORB Compiler
 */ 
public class porteHelper
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
     * Insert porte into an any
     * @param a an any
     * @param t porte value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.porte t)
    {
        a.insert_Streamable(new Gestion_acces.porteHolder(t));
    }

    /**
     * Extract porte from an any
     * @param a an any
     * @return the extracted porte value
     */
    public static Gestion_acces.porte extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.porteHolder)
                    return ((Gestion_acces.porteHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.porteHolder h = new Gestion_acces.porteHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the porte TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[3];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "idPorte";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "libellePorte";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "refZone";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_struct_tc(id(),"porte",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the porte IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/porte:1.0";

    /**
     * Read porte from a marshalled stream
     * @param istream the input stream
     * @return the readed porte value
     */
    public static Gestion_acces.porte read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.porte new_one = new Gestion_acces.porte();

        new_one.idPorte = istream.read_short();
        new_one.libellePorte = istream.read_string();
        new_one.refZone = istream.read_short();

        return new_one;
    }

    /**
     * Write porte into a marshalled stream
     * @param ostream the output stream
     * @param value porte value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.porte value)
    {
        ostream.write_short(value.idPorte);
        ostream.write_string(value.libellePorte);
        ostream.write_short(value.refZone);
    }

}

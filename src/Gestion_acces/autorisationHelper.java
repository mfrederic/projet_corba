package Gestion_acces;

/** 
 * Helper class for : autorisation
 *  
 * @author OpenORB Compiler
 */ 
public class autorisationHelper
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
     * Insert autorisation into an any
     * @param a an any
     * @param t autorisation value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.autorisation t)
    {
        a.insert_Streamable(new Gestion_acces.autorisationHolder(t));
    }

    /**
     * Extract autorisation from an any
     * @param a an any
     * @return the extracted autorisation value
     */
    public static Gestion_acces.autorisation extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.autorisationHolder)
                    return ((Gestion_acces.autorisationHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.autorisationHolder h = new Gestion_acces.autorisationHolder(read(a.create_input_stream()));
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
     * Return the autorisation TypeCode
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
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[4];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "numAuto";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "refPers";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "sP";
                _members[2].type = Gestion_acces.structPlageHelper.type();
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "refZone";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_struct_tc(id(),"autorisation",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the autorisation IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/autorisation:1.0";

    /**
     * Read autorisation from a marshalled stream
     * @param istream the input stream
     * @return the readed autorisation value
     */
    public static Gestion_acces.autorisation read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.autorisation new_one = new Gestion_acces.autorisation();

        new_one.numAuto = istream.read_short();
        new_one.refPers = istream.read_short();
        new_one.sP = Gestion_acces.structPlageHelper.read(istream);
        new_one.refZone = istream.read_short();

        return new_one;
    }

    /**
     * Write autorisation into a marshalled stream
     * @param ostream the output stream
     * @param value autorisation value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.autorisation value)
    {
        ostream.write_short(value.numAuto);
        ostream.write_short(value.refPers);
        Gestion_acces.structPlageHelper.write(ostream,value.sP);
        ostream.write_short(value.refZone);
    }

}

package Gestion_acces;

/** 
 * Helper class for : structPlage
 *  
 * @author OpenORB Compiler
 */ 
public class structPlageHelper
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
     * Insert structPlage into an any
     * @param a an any
     * @param t structPlage value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.structPlage t)
    {
        a.insert_Streamable(new Gestion_acces.structPlageHolder(t));
    }

    /**
     * Extract structPlage from an any
     * @param a an any
     * @return the extracted structPlage value
     */
    public static Gestion_acces.structPlage extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.structPlageHolder)
                    return ((Gestion_acces.structPlageHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.structPlageHolder h = new Gestion_acces.structPlageHolder(read(a.create_input_stream()));
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
     * Return the structPlage TypeCode
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
                _members[0].name = "jourDeb";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "jourFin";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "heureDeb";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_float);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "heureFin";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_float);
                _tc = orb.create_struct_tc(id(),"structPlage",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the structPlage IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/structPlage:1.0";

    /**
     * Read structPlage from a marshalled stream
     * @param istream the input stream
     * @return the readed structPlage value
     */
    public static Gestion_acces.structPlage read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.structPlage new_one = new Gestion_acces.structPlage();

        new_one.jourDeb = istream.read_string();
        new_one.jourFin = istream.read_string();
        new_one.heureDeb = istream.read_float();
        new_one.heureFin = istream.read_float();

        return new_one;
    }

    /**
     * Write structPlage into a marshalled stream
     * @param ostream the output stream
     * @param value structPlage value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.structPlage value)
    {
        ostream.write_string(value.jourDeb);
        ostream.write_string(value.jourFin);
        ostream.write_float(value.heureDeb);
        ostream.write_float(value.heureFin);
    }

}

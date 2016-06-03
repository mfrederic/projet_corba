package Gestion_acces.ServeurAutorisationPackage;

/** 
 * Helper class for : zoneInconnue
 *  
 * @author OpenORB Compiler
 */ 
public class zoneInconnueHelper
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
     * Insert zoneInconnue into an any
     * @param a an any
     * @param t zoneInconnue value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.ServeurAutorisationPackage.zoneInconnue t)
    {
        a.insert_Streamable(new Gestion_acces.ServeurAutorisationPackage.zoneInconnueHolder(t));
    }

    /**
     * Extract zoneInconnue from an any
     * @param a an any
     * @return the extracted zoneInconnue value
     */
    public static Gestion_acces.ServeurAutorisationPackage.zoneInconnue extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.ServeurAutorisationPackage.zoneInconnueHolder)
                    return ((Gestion_acces.ServeurAutorisationPackage.zoneInconnueHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.ServeurAutorisationPackage.zoneInconnueHolder h = new Gestion_acces.ServeurAutorisationPackage.zoneInconnueHolder(read(a.create_input_stream()));
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
     * Return the zoneInconnue TypeCode
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
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[1];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "zone";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_exception_tc(id(),"zoneInconnue",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the zoneInconnue IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/ServeurAutorisation/zoneInconnue:1.0";

    /**
     * Read zoneInconnue from a marshalled stream
     * @param istream the input stream
     * @return the readed zoneInconnue value
     */
    public static Gestion_acces.ServeurAutorisationPackage.zoneInconnue read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.ServeurAutorisationPackage.zoneInconnue new_one = new Gestion_acces.ServeurAutorisationPackage.zoneInconnue();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.zone = istream.read_short();

        return new_one;
    }

    /**
     * Write zoneInconnue into a marshalled stream
     * @param ostream the output stream
     * @param value zoneInconnue value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.ServeurAutorisationPackage.zoneInconnue value)
    {
        ostream.write_string(id());
        ostream.write_short(value.zone);
    }

}

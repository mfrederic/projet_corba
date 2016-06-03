package Gestion_acces;

/** 
 * Helper class for : droitsInsuffisants
 *  
 * @author OpenORB Compiler
 */ 
public class droitsInsuffisantsHelper
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
     * Insert droitsInsuffisants into an any
     * @param a an any
     * @param t droitsInsuffisants value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.droitsInsuffisants t)
    {
        a.insert_Streamable(new Gestion_acces.droitsInsuffisantsHolder(t));
    }

    /**
     * Extract droitsInsuffisants from an any
     * @param a an any
     * @return the extracted droitsInsuffisants value
     */
    public static Gestion_acces.droitsInsuffisants extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.droitsInsuffisantsHolder)
                    return ((Gestion_acces.droitsInsuffisantsHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.droitsInsuffisantsHolder h = new Gestion_acces.droitsInsuffisantsHolder(read(a.create_input_stream()));
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
     * Return the droitsInsuffisants TypeCode
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
                _members[0].name = "raison";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _tc = orb.create_exception_tc(id(),"droitsInsuffisants",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the droitsInsuffisants IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/droitsInsuffisants:1.0";

    /**
     * Read droitsInsuffisants from a marshalled stream
     * @param istream the input stream
     * @return the readed droitsInsuffisants value
     */
    public static Gestion_acces.droitsInsuffisants read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.droitsInsuffisants new_one = new Gestion_acces.droitsInsuffisants();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.raison = istream.read_string();

        return new_one;
    }

    /**
     * Write droitsInsuffisants into a marshalled stream
     * @param ostream the output stream
     * @param value droitsInsuffisants value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.droitsInsuffisants value)
    {
        ostream.write_string(id());
        ostream.write_string(value.raison);
    }

}

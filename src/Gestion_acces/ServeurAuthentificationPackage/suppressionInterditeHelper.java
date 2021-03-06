package Gestion_acces.ServeurAuthentificationPackage;

/** 
 * Helper class for : suppressionInterdite
 *  
 * @author OpenORB Compiler
 */ 
public class suppressionInterditeHelper
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
     * Insert suppressionInterdite into an any
     * @param a an any
     * @param t suppressionInterdite value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite t)
    {
        a.insert_Streamable(new Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHolder(t));
    }

    /**
     * Extract suppressionInterdite from an any
     * @param a an any
     * @return the extracted suppressionInterdite value
     */
    public static Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHolder)
                    return ((Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHolder h = new Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHolder(read(a.create_input_stream()));
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
     * Return the suppressionInterdite TypeCode
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
                _members[0].name = "statut";
                _members[0].type = Gestion_acces.statutPersonneHelper.type();
                _tc = orb.create_exception_tc(id(),"suppressionInterdite",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the suppressionInterdite IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/ServeurAuthentification/suppressionInterdite:1.0";

    /**
     * Read suppressionInterdite from a marshalled stream
     * @param istream the input stream
     * @return the readed suppressionInterdite value
     */
    public static Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite new_one = new Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.statut = Gestion_acces.statutPersonneHelper.read(istream);

        return new_one;
    }

    /**
     * Write suppressionInterdite into a marshalled stream
     * @param ostream the output stream
     * @param value suppressionInterdite value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite value)
    {
        ostream.write_string(id());
        Gestion_acces.statutPersonneHelper.write(ostream,value.statut);
    }

}

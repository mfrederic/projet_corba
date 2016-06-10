package Gestion_acces.AnnuairePackage;

/** 
 * Helper class for : personneInexistante
 *  
 * @author OpenORB Compiler
 */ 
public class personneInexistanteHelper
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
     * Insert personneInexistante into an any
     * @param a an any
     * @param t personneInexistante value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.AnnuairePackage.personneInexistante t)
    {
        a.insert_Streamable(new Gestion_acces.AnnuairePackage.personneInexistanteHolder(t));
    }

    /**
     * Extract personneInexistante from an any
     * @param a an any
     * @return the extracted personneInexistante value
     */
    public static Gestion_acces.AnnuairePackage.personneInexistante extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.AnnuairePackage.personneInexistanteHolder)
                    return ((Gestion_acces.AnnuairePackage.personneInexistanteHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.AnnuairePackage.personneInexistanteHolder h = new Gestion_acces.AnnuairePackage.personneInexistanteHolder(read(a.create_input_stream()));
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
     * Return the personneInexistante TypeCode
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
                _members[0].name = "id";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _tc = orb.create_exception_tc(id(),"personneInexistante",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the personneInexistante IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/Annuaire/personneInexistante:1.0";

    /**
     * Read personneInexistante from a marshalled stream
     * @param istream the input stream
     * @return the readed personneInexistante value
     */
    public static Gestion_acces.AnnuairePackage.personneInexistante read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.AnnuairePackage.personneInexistante new_one = new Gestion_acces.AnnuairePackage.personneInexistante();

        if (!istream.read_string().equals(id()))
         throw new org.omg.CORBA.MARSHAL();
        new_one.id = istream.read_short();

        return new_one;
    }

    /**
     * Write personneInexistante into a marshalled stream
     * @param ostream the output stream
     * @param value personneInexistante value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.AnnuairePackage.personneInexistante value)
    {
        ostream.write_string(id());
        ostream.write_short(value.id);
    }

}
package Gestion_acces;

/** 
 * Helper class for : log
 *  
 * @author OpenORB Compiler
 */ 
public class logHelper
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
     * Insert log into an any
     * @param a an any
     * @param t log value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.log t)
    {
        a.insert_Streamable(new Gestion_acces.logHolder(t));
    }

    /**
     * Extract log from an any
     * @param a an any
     * @return the extracted log value
     */
    public static Gestion_acces.log extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.logHolder)
                    return ((Gestion_acces.logHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.logHolder h = new Gestion_acces.logHolder(read(a.create_input_stream()));
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
     * Return the log TypeCode
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
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[6];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "idJournal";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "timestamp";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "typeAcces";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "refPersonne";
                _members[3].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[4] = new org.omg.CORBA.StructMember();
                _members[4].name = "resultat";
                _members[4].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_boolean);
                _members[5] = new org.omg.CORBA.StructMember();
                _members[5].name = "commentaire";
                _members[5].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _tc = orb.create_struct_tc(id(),"log",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the log IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/log:1.0";

    /**
     * Read log from a marshalled stream
     * @param istream the input stream
     * @return the readed log value
     */
    public static Gestion_acces.log read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.log new_one = new Gestion_acces.log();

        new_one.idJournal = istream.read_short();
        new_one.timestamp = istream.read_string();
        new_one.typeAcces = istream.read_string();
        new_one.refPersonne = istream.read_short();
        new_one.resultat = istream.read_boolean();
        new_one.commentaire = istream.read_string();

        return new_one;
    }

    /**
     * Write log into a marshalled stream
     * @param ostream the output stream
     * @param value log value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.log value)
    {
        ostream.write_short(value.idJournal);
        ostream.write_string(value.timestamp);
        ostream.write_string(value.typeAcces);
        ostream.write_short(value.refPersonne);
        ostream.write_boolean(value.resultat);
        ostream.write_string(value.commentaire);
    }

}

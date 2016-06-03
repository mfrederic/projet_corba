package Gestion_acces;

/** 
 * Helper class for : personne
 *  
 * @author OpenORB Compiler
 */ 
public class personneHelper
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
     * Insert personne into an any
     * @param a an any
     * @param t personne value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.personne t)
    {
        a.insert_Streamable(new Gestion_acces.personneHolder(t));
    }

    /**
     * Extract personne from an any
     * @param a an any
     * @return the extracted personne value
     */
    public static Gestion_acces.personne extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof Gestion_acces.personneHolder)
                    return ((Gestion_acces.personneHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            Gestion_acces.personneHolder h = new Gestion_acces.personneHolder(read(a.create_input_stream()));
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
     * Return the personne TypeCode
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
                _members[0].name = "idPers";
                _members[0].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_short);
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "nom";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "prenom";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[3] = new org.omg.CORBA.StructMember();
                _members[3].name = "ph";
                _members[3].type = Gestion_acces.photoHelper.type();
                _members[4] = new org.omg.CORBA.StructMember();
                _members[4].name = "statut";
                _members[4].type = Gestion_acces.statutPersonneHelper.type();
                _members[5] = new org.omg.CORBA.StructMember();
                _members[5].name = "role";
                _members[5].type = Gestion_acces.rolePersonneHelper.type();
                _tc = orb.create_struct_tc(id(),"personne",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the personne IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/personne:1.0";

    /**
     * Read personne from a marshalled stream
     * @param istream the input stream
     * @return the readed personne value
     */
    public static Gestion_acces.personne read(org.omg.CORBA.portable.InputStream istream)
    {
        Gestion_acces.personne new_one = new Gestion_acces.personne();

        new_one.idPers = istream.read_short();
        new_one.nom = istream.read_string();
        new_one.prenom = istream.read_string();
        new_one.ph = Gestion_acces.photoHelper.read(istream);
        new_one.statut = Gestion_acces.statutPersonneHelper.read(istream);
        new_one.role = Gestion_acces.rolePersonneHelper.read(istream);

        return new_one;
    }

    /**
     * Write personne into a marshalled stream
     * @param ostream the output stream
     * @param value personne value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.personne value)
    {
        ostream.write_short(value.idPers);
        ostream.write_string(value.nom);
        ostream.write_string(value.prenom);
        Gestion_acces.photoHelper.write(ostream,value.ph);
        Gestion_acces.statutPersonneHelper.write(ostream,value.statut);
        Gestion_acces.rolePersonneHelper.write(ostream,value.role);
    }

}

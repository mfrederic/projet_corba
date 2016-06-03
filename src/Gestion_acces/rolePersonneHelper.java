package Gestion_acces;

/** 
 * Helper class for : rolePersonne
 *  
 * @author OpenORB Compiler
 */ 
public class rolePersonneHelper
{
    /**
     * Insert rolePersonne into an any
     * @param a an any
     * @param t rolePersonne value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.rolePersonne t)
    {
        a.type(type());
        write(a.create_output_stream(),t);
    }

    /**
     * Extract rolePersonne from an any
     * @param a an any
     * @return the extracted rolePersonne value
     */
    public static Gestion_acces.rolePersonne extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the rolePersonne TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            String []_members = new String[3];
            _members[0] = "RH";
            _members[1] = "accueil";
            _members[2] = "basique";
            _tc = orb.create_enum_tc(id(),"rolePersonne",_members);
        }
        return _tc;
    }

    /**
     * Return the rolePersonne IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/rolePersonne:1.0";

    /**
     * Read rolePersonne from a marshalled stream
     * @param istream the input stream
     * @return the readed rolePersonne value
     */
    public static Gestion_acces.rolePersonne read(org.omg.CORBA.portable.InputStream istream)
    {
        return rolePersonne.from_int(istream.read_ulong());
    }

    /**
     * Write rolePersonne into a marshalled stream
     * @param ostream the output stream
     * @param value rolePersonne value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.rolePersonne value)
    {
        ostream.write_ulong(value.value());
    }

}

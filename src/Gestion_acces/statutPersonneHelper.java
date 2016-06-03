package Gestion_acces;

/** 
 * Helper class for : statutPersonne
 *  
 * @author OpenORB Compiler
 */ 
public class statutPersonneHelper
{
    /**
     * Insert statutPersonne into an any
     * @param a an any
     * @param t statutPersonne value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.statutPersonne t)
    {
        a.type(type());
        write(a.create_output_stream(),t);
    }

    /**
     * Extract statutPersonne from an any
     * @param a an any
     * @return the extracted statutPersonne value
     */
    public static Gestion_acces.statutPersonne extract(org.omg.CORBA.Any a)
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
     * Return the statutPersonne TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            String []_members = new String[2];
            _members[0] = "temporaire";
            _members[1] = "permanent";
            _tc = orb.create_enum_tc(id(),"statutPersonne",_members);
        }
        return _tc;
    }

    /**
     * Return the statutPersonne IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/statutPersonne:1.0";

    /**
     * Read statutPersonne from a marshalled stream
     * @param istream the input stream
     * @return the readed statutPersonne value
     */
    public static Gestion_acces.statutPersonne read(org.omg.CORBA.portable.InputStream istream)
    {
        return statutPersonne.from_int(istream.read_ulong());
    }

    /**
     * Write statutPersonne into a marshalled stream
     * @param ostream the output stream
     * @param value statutPersonne value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.statutPersonne value)
    {
        ostream.write_ulong(value.value());
    }

}

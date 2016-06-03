package Gestion_acces;

/** 
 * Helper class for : ServeurAuthentification
 *  
 * @author OpenORB Compiler
 */ 
public class ServeurAuthentificationHelper
{
    /**
     * Insert ServeurAuthentification into an any
     * @param a an any
     * @param t ServeurAuthentification value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.ServeurAuthentification t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract ServeurAuthentification from an any
     * @param a an any
     * @return the extracted ServeurAuthentification value
     */
    public static Gestion_acces.ServeurAuthentification extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return Gestion_acces.ServeurAuthentificationHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the ServeurAuthentification TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"ServeurAuthentification");
        }
        return _tc;
    }

    /**
     * Return the ServeurAuthentification IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/ServeurAuthentification:1.0";

    /**
     * Read ServeurAuthentification from a marshalled stream
     * @param istream the input stream
     * @return the readed ServeurAuthentification value
     */
    public static Gestion_acces.ServeurAuthentification read(org.omg.CORBA.portable.InputStream istream)
    {
        return(Gestion_acces.ServeurAuthentification)istream.read_Object(Gestion_acces._ServeurAuthentificationStub.class);
    }

    /**
     * Write ServeurAuthentification into a marshalled stream
     * @param ostream the output stream
     * @param value ServeurAuthentification value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.ServeurAuthentification value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to ServeurAuthentification
     * @param obj the CORBA Object
     * @return ServeurAuthentification Object
     */
    public static ServeurAuthentification narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof ServeurAuthentification)
            return (ServeurAuthentification)obj;

        if (obj._is_a(id()))
        {
            _ServeurAuthentificationStub stub = new _ServeurAuthentificationStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to ServeurAuthentification
     * @param obj the CORBA Object
     * @return ServeurAuthentification Object
     */
    public static ServeurAuthentification unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof ServeurAuthentification)
            return (ServeurAuthentification)obj;

        _ServeurAuthentificationStub stub = new _ServeurAuthentificationStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}

package Gestion_acces;

/** 
 * Helper class for : ServeurAutorisation
 *  
 * @author OpenORB Compiler
 */ 
public class ServeurAutorisationHelper
{
    /**
     * Insert ServeurAutorisation into an any
     * @param a an any
     * @param t ServeurAutorisation value
     */
    public static void insert(org.omg.CORBA.Any a, Gestion_acces.ServeurAutorisation t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract ServeurAutorisation from an any
     * @param a an any
     * @return the extracted ServeurAutorisation value
     */
    public static Gestion_acces.ServeurAutorisation extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return Gestion_acces.ServeurAutorisationHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the ServeurAutorisation TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"ServeurAutorisation");
        }
        return _tc;
    }

    /**
     * Return the ServeurAutorisation IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:Gestion_acces/ServeurAutorisation:1.0";

    /**
     * Read ServeurAutorisation from a marshalled stream
     * @param istream the input stream
     * @return the readed ServeurAutorisation value
     */
    public static Gestion_acces.ServeurAutorisation read(org.omg.CORBA.portable.InputStream istream)
    {
        return(Gestion_acces.ServeurAutorisation)istream.read_Object(Gestion_acces._ServeurAutorisationStub.class);
    }

    /**
     * Write ServeurAutorisation into a marshalled stream
     * @param ostream the output stream
     * @param value ServeurAutorisation value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, Gestion_acces.ServeurAutorisation value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to ServeurAutorisation
     * @param obj the CORBA Object
     * @return ServeurAutorisation Object
     */
    public static ServeurAutorisation narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof ServeurAutorisation)
            return (ServeurAutorisation)obj;

        if (obj._is_a(id()))
        {
            _ServeurAutorisationStub stub = new _ServeurAutorisationStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to ServeurAutorisation
     * @param obj the CORBA Object
     * @return ServeurAutorisation Object
     */
    public static ServeurAutorisation unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof ServeurAutorisation)
            return (ServeurAutorisation)obj;

        _ServeurAutorisationStub stub = new _ServeurAutorisationStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}

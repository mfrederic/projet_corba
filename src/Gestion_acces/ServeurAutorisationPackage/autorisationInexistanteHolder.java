package Gestion_acces.ServeurAutorisationPackage;

/**
 * Holder class for : autorisationInexistante
 * 
 * @author OpenORB Compiler
 */
final public class autorisationInexistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal autorisationInexistante value
     */
    public Gestion_acces.ServeurAutorisationPackage.autorisationInexistante value;

    /**
     * Default constructor
     */
    public autorisationInexistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public autorisationInexistanteHolder(Gestion_acces.ServeurAutorisationPackage.autorisationInexistante initial)
    {
        value = initial;
    }

    /**
     * Read autorisationInexistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = autorisationInexistanteHelper.read(istream);
    }

    /**
     * Write autorisationInexistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        autorisationInexistanteHelper.write(ostream,value);
    }

    /**
     * Return the autorisationInexistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return autorisationInexistanteHelper.type();
    }

}

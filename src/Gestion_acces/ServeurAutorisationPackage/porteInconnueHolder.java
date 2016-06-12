package Gestion_acces.ServeurAutorisationPackage;

/**
 * Holder class for : porteInconnue
 * 
 * @author OpenORB Compiler
 */
final public class porteInconnueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal porteInconnue value
     */
    public Gestion_acces.ServeurAutorisationPackage.porteInconnue value;

    /**
     * Default constructor
     */
    public porteInconnueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public porteInconnueHolder(Gestion_acces.ServeurAutorisationPackage.porteInconnue initial)
    {
        value = initial;
    }

    /**
     * Read porteInconnue from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = porteInconnueHelper.read(istream);
    }

    /**
     * Write porteInconnue into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        porteInconnueHelper.write(ostream,value);
    }

    /**
     * Return the porteInconnue TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return porteInconnueHelper.type();
    }

}

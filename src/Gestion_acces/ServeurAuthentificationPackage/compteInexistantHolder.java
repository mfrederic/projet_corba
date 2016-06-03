package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Holder class for : compteInexistant
 * 
 * @author OpenORB Compiler
 */
final public class compteInexistantHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal compteInexistant value
     */
    public Gestion_acces.ServeurAuthentificationPackage.compteInexistant value;

    /**
     * Default constructor
     */
    public compteInexistantHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public compteInexistantHolder(Gestion_acces.ServeurAuthentificationPackage.compteInexistant initial)
    {
        value = initial;
    }

    /**
     * Read compteInexistant from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = compteInexistantHelper.read(istream);
    }

    /**
     * Write compteInexistant into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        compteInexistantHelper.write(ostream,value);
    }

    /**
     * Return the compteInexistant TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return compteInexistantHelper.type();
    }

}

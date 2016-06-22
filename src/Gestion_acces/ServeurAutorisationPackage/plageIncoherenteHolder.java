package Gestion_acces.ServeurAutorisationPackage;

/**
 * Holder class for : plageIncoherente
 * 
 * @author OpenORB Compiler
 */
final public class plageIncoherenteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal plageIncoherente value
     */
    public Gestion_acces.ServeurAutorisationPackage.plageIncoherente value;

    /**
     * Default constructor
     */
    public plageIncoherenteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public plageIncoherenteHolder(Gestion_acces.ServeurAutorisationPackage.plageIncoherente initial)
    {
        value = initial;
    }

    /**
     * Read plageIncoherente from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = plageIncoherenteHelper.read(istream);
    }

    /**
     * Write plageIncoherente into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        plageIncoherenteHelper.write(ostream,value);
    }

    /**
     * Return the plageIncoherente TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return plageIncoherenteHelper.type();
    }

}

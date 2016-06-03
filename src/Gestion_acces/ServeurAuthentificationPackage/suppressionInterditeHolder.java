package Gestion_acces.ServeurAuthentificationPackage;

/**
 * Holder class for : suppressionInterdite
 * 
 * @author OpenORB Compiler
 */
final public class suppressionInterditeHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal suppressionInterdite value
     */
    public Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite value;

    /**
     * Default constructor
     */
    public suppressionInterditeHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public suppressionInterditeHolder(Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite initial)
    {
        value = initial;
    }

    /**
     * Read suppressionInterdite from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = suppressionInterditeHelper.read(istream);
    }

    /**
     * Write suppressionInterdite into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        suppressionInterditeHelper.write(ostream,value);
    }

    /**
     * Return the suppressionInterdite TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return suppressionInterditeHelper.type();
    }

}

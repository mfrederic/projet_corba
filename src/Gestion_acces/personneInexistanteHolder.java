package Gestion_acces;

/**
 * Holder class for : personneInexistante
 * 
 * @author OpenORB Compiler
 */
final public class personneInexistanteHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal personneInexistante value
     */
    public Gestion_acces.personneInexistante value;

    /**
     * Default constructor
     */
    public personneInexistanteHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public personneInexistanteHolder(Gestion_acces.personneInexistante initial)
    {
        value = initial;
    }

    /**
     * Read personneInexistante from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = personneInexistanteHelper.read(istream);
    }

    /**
     * Write personneInexistante into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        personneInexistanteHelper.write(ostream,value);
    }

    /**
     * Return the personneInexistante TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return personneInexistanteHelper.type();
    }

}

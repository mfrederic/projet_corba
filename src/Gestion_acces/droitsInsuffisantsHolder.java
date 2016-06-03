package Gestion_acces;

/**
 * Holder class for : droitsInsuffisants
 * 
 * @author OpenORB Compiler
 */
final public class droitsInsuffisantsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal droitsInsuffisants value
     */
    public Gestion_acces.droitsInsuffisants value;

    /**
     * Default constructor
     */
    public droitsInsuffisantsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public droitsInsuffisantsHolder(Gestion_acces.droitsInsuffisants initial)
    {
        value = initial;
    }

    /**
     * Read droitsInsuffisants from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = droitsInsuffisantsHelper.read(istream);
    }

    /**
     * Write droitsInsuffisants into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        droitsInsuffisantsHelper.write(ostream,value);
    }

    /**
     * Return the droitsInsuffisants TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return droitsInsuffisantsHelper.type();
    }

}

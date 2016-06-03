package Gestion_acces;

/**
 * Holder class for : listePersonnes
 * 
 * @author OpenORB Compiler
 */
final public class listePersonnesHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal listePersonnes value
     */
    public Gestion_acces.personne[] value;

    /**
     * Default constructor
     */
    public listePersonnesHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public listePersonnesHolder(Gestion_acces.personne[] initial)
    {
        value = initial;
    }

    /**
     * Read listePersonnes from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = listePersonnesHelper.read(istream);
    }

    /**
     * Write listePersonnes into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        listePersonnesHelper.write(ostream,value);
    }

    /**
     * Return the listePersonnes TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return listePersonnesHelper.type();
    }

}

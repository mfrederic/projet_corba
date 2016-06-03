package Gestion_acces;

/**
 * Enum definition : statutPersonne
 *
 * @author OpenORB Compiler
*/
public final class statutPersonne implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Enum member temporaire value 
     */
    public static final int _temporaire = 0;

    /**
     * Enum member temporaire
     */
    public static final statutPersonne temporaire = new statutPersonne(_temporaire);

    /**
     * Enum member permanent value 
     */
    public static final int _permanent = 1;

    /**
     * Enum member permanent
     */
    public static final statutPersonne permanent = new statutPersonne(_permanent);

    /**
     * Internal member value 
     */
    private final int _statutPersonne_value;

    /**
     * Private constructor
     * @param  the enum value for this new member
     */
    private statutPersonne( final int value )
    {
        _statutPersonne_value = value;
    }

    /**
     * Maintains singleton property for serialized enums.
     * Issue 4271: IDL/Java issue, Mapping for IDL enum.
     */
    public java.lang.Object readResolve() throws java.io.ObjectStreamException
    {
        return from_int( value() );
    }

    /**
     * Return the internal member value
     * @return the member value
     */
    public int value()
    {
        return _statutPersonne_value;
    }

    /**
     * Return a enum member from its value
     * @param  an enum value
     * @return an enum member
         */
    public static statutPersonne from_int(int value)
    {
        switch (value)
        {
        case 0 :
            return temporaire;
        case 1 :
            return permanent;
        }
        throw new org.omg.CORBA.BAD_OPERATION();
    }

    /**
     * Return a string representation
     * @return a string representation of the enumeration
     */
    public java.lang.String toString()
    {
        switch (_statutPersonne_value)
        {
        case 0 :
            return "temporaire";
        case 1 :
            return "permanent";
        }
        throw new org.omg.CORBA.BAD_OPERATION();
    }

}

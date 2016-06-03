package Gestion_acces;

/**
 * Enum definition : rolePersonne
 *
 * @author OpenORB Compiler
*/
public final class rolePersonne implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Enum member RH value 
     */
    public static final int _RH = 0;

    /**
     * Enum member RH
     */
    public static final rolePersonne RH = new rolePersonne(_RH);

    /**
     * Enum member accueil value 
     */
    public static final int _accueil = 1;

    /**
     * Enum member accueil
     */
    public static final rolePersonne accueil = new rolePersonne(_accueil);

    /**
     * Enum member basique value 
     */
    public static final int _basique = 2;

    /**
     * Enum member basique
     */
    public static final rolePersonne basique = new rolePersonne(_basique);

    /**
     * Internal member value 
     */
    private final int _rolePersonne_value;

    /**
     * Private constructor
     * @param  the enum value for this new member
     */
    private rolePersonne( final int value )
    {
        _rolePersonne_value = value;
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
        return _rolePersonne_value;
    }

    /**
     * Return a enum member from its value
     * @param  an enum value
     * @return an enum member
         */
    public static rolePersonne from_int(int value)
    {
        switch (value)
        {
        case 0 :
            return RH;
        case 1 :
            return accueil;
        case 2 :
            return basique;
        }
        throw new org.omg.CORBA.BAD_OPERATION();
    }

    /**
     * Return a string representation
     * @return a string representation of the enumeration
     */
    public java.lang.String toString()
    {
        switch (_rolePersonne_value)
        {
        case 0 :
            return "RH";
        case 1 :
            return "accueil";
        case 2 :
            return "basique";
        }
        throw new org.omg.CORBA.BAD_OPERATION();
    }

}

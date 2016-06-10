package Gestion_acces;

/**
 * Interface definition : ServeurAutorisation
 * 
 * @author OpenORB Compiler
 */
public class ServeurAutorisationPOATie extends ServeurAutorisationPOA
{

    //
    // Private reference to implementation object
    //
    private ServeurAutorisationOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public ServeurAutorisationPOATie(ServeurAutorisationOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public ServeurAutorisationPOATie(ServeurAutorisationOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public ServeurAutorisationOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(ServeurAutorisationOperations delegate_)
    {
        _tie = delegate_;
    }

    /**
     * _default_POA method
     */
    public org.omg.PortableServer.POA _default_POA()
    {
        if (_poa != null)
            return _poa;
        else
            return super._default_POA();
    }

    /**
     * Operation demanderAutor
     */
    public boolean demanderAutor(Gestion_acces.personne p, short zone)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue
    {
        return _tie.demanderAutor( p,  zone);
    }

    /**
     * Operation ajouterAutorisation
     */
    public void ajouterAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue
    {
        _tie.ajouterAutorisation( p,  zone,  plage);
    }

    /**
     * Operation modifierAutorisation
     */
    public void modifierAutorisation(Gestion_acces.personne p, short oldZone, Gestion_acces.structPlage oldPlage, short newZone, Gestion_acces.structPlage newPlage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.autorisationInexistante
    {
        _tie.modifierAutorisation( p,  oldZone,  oldPlage,  newZone,  newPlage);
    }

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.autorisationInexistante
    {
        _tie.supprimerAutorisation( p,  zone,  plage);
    }

    /**
     * Operation getZonesResp
     */
    public short[] getZonesResp(Gestion_acces.personne resp)
    {
        return _tie.getZonesResp( resp);
    }

}

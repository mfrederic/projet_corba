package Gestion_acces;

/**
 * Interface definition : ServeurAuthentification
 * 
 * @author OpenORB Compiler
 */
public class ServeurAuthentificationPOATie extends ServeurAuthentificationPOA
{

    //
    // Private reference to implementation object
    //
    private ServeurAuthentificationOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public ServeurAuthentificationPOATie(ServeurAuthentificationOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public ServeurAuthentificationPOATie(ServeurAuthentificationOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public ServeurAuthentificationOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(ServeurAuthentificationOperations delegate_)
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
     * Operation demanderAuth
     */
    public Gestion_acces.personne demanderAuth(String emp, String ph, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse
    {
        return _tie.demanderAuth( emp,  ph,  mdp);
    }

    /**
     * Operation authentifier
     */
    public Gestion_acces.personne authentifier(String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants, Gestion_acces.ServeurAuthentificationPackage.accesRefuse
    {
        return _tie.authentifier( user,  password,  mdp);
    }

    /**
     * Operation ajouterEmpreinte
     */
    public void ajouterEmpreinte(String user, String emp, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.empreinteDejaExistante
    {
        _tie.ajouterEmpreinte( user,  emp,  mdp);
    }

    /**
     * Operation modifierEmpreinte
     */
    public void modifierEmpreinte(String user, String emp, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants, Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant
    {
        _tie.modifierEmpreinte( user,  emp,  mdp);
    }

    /**
     * Operation supprimerEmpreinte
     */
    public void supprimerEmpreinte(String user, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite
    {
        _tie.supprimerEmpreinte( user,  mdp);
    }

    /**
     * Operation creerCompte
     */
    public void creerCompte(short idPersonne, String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteDejaCree, Gestion_acces.ServeurAuthentificationPackage.accesRefuse
    {
        _tie.creerCompte( idPersonne,  user,  password,  mdp);
    }

    /**
     * Operation supprimerCompte
     */
    public short supprimerCompte(String user, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant
    {
        return _tie.supprimerCompte( user,  mdp);
    }

    /**
     * Operation modifierMdp
     */
    public void modifierMdp(String user, String newMdp, String mdpServeur)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.accesRefuse
    {
        _tie.modifierMdp( user,  newMdp,  mdpServeur);
    }

}

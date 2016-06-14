package Gestion_acces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public class AnnuairePOATie extends AnnuairePOA
{

    //
    // Private reference to implementation object
    //
    private AnnuaireOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public AnnuairePOATie(AnnuaireOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public AnnuairePOATie(AnnuaireOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public AnnuaireOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(AnnuaireOperations delegate_)
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
     * Operation identifier
     */
    public Gestion_acces.personne identifier(short id)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        return _tie.identifier( id);
    }

    /**
     * Operation demanderIdentite
     */
    public Gestion_acces.personne demanderIdentite(String ph)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        return _tie.demanderIdentite( ph);
    }

    /**
     * Operation creerPersonne
     */
    public short creerPersonne(String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
    {
        return _tie.creerPersonne( nom,  prenom,  statut,  role);
    }

    /**
     * Operation ajouterPhoto
     */
    public void ajouterPhoto(short idPersonne, String ph)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        _tie.ajouterPhoto( idPersonne,  ph);
    }

    /**
     * Operation modifierInfos
     */
    public void modifierInfos(short idPersonne, String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        _tie.modifierInfos( idPersonne,  nom,  prenom,  statut,  role);
    }

    /**
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(short idPersonne)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        _tie.supprimerPersonne( idPersonne);
    }

    /**
     * Operation chercherPersonnes
     */
    public Gestion_acces.personne[] chercherPersonnes(String nom, String prenom)
    {
        return _tie.chercherPersonnes( nom,  prenom);
    }

}

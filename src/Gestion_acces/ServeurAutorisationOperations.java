package Gestion_acces;

/**
 * Interface definition : ServeurAutorisation
 * 
 * @author OpenORB Compiler
 */
public interface ServeurAutorisationOperations
{
    /**
     * Operation demanderAutor
     */
    public boolean demanderAutor(Gestion_acces.personne p, short idPorte, String date)
        throws Gestion_acces.ServeurAutorisationPackage.porteInconnue;

    /**
     * Operation ajouterAutorisation
     */
    public void ajouterAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue;

    /**
     * Operation modifierAutorisation
     */
    public void modifierAutorisation(short numAutor, Gestion_acces.structPlage newPlage)
        throws Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(short numAutor)
        throws Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;

    /**
     * Operation getAutorisationsResp
     */
    public Gestion_acces.autorisation[] getAutorisationsResp(short[] zones);

    /**
     * Operation getZonesResp
     */
    public short[] getZonesResp(Gestion_acces.personne resp);

    /**
     * Operation getPortes
     */
    public Gestion_acces.porte[] getPortes();

}

package Gestion_acces;

/**
 * Interface definition : ServeurAuthentification
 * 
 * @author OpenORB Compiler
 */
public interface ServeurAuthentificationOperations
{
    /**
     * Operation demanderAuth
     */
    public Gestion_acces.personne demanderAuth(String emp, String ph, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse;

    /**
     * Operation authentifier
     */
    public Gestion_acces.personne authentifier(String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants, Gestion_acces.ServeurAuthentificationPackage.accesRefuse;

    /**
     * Operation ajouterEmpreinte
     */
    public void ajouterEmpreinte(String user, String emp, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant;

    /**
     * Operation supprimerEmpreinte
     */
    public void supprimerEmpreinte(String user, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite;

    /**
     * Operation creerCompte
     */
    public void creerCompte(short idPersonne, String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteDejaCree, Gestion_acces.ServeurAuthentificationPackage.accesRefuse;

    /**
     * Operation modifierMdp
     */
    public void modifierMdp(String user, String newMdp, String mdpServeur)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.accesRefuse;

}

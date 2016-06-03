package Gestion_acces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public interface AnnuaireOperations
{
    /**
     * Operation identifier
     */
    public Gestion_acces.personne identifier(short id)
        throws Gestion_acces.personneInexistante;

    /**
     * Operation demanderIdentite
     */
    public Gestion_acces.personne demanderIdentite(String ph)
        throws Gestion_acces.personneInexistante;

    /**
     * Operation creerPersonne
     */
    public short creerPersonne(String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role);

    /**
     * Operation ajouterPhoto
     */
    public void ajouterPhoto(short idPersonne, String ph)
        throws Gestion_acces.personneInexistante;

    /**
     * Operation modifierInfos
     */
    public void modifierInfos(short idPersonne, String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
        throws Gestion_acces.personneInexistante;

    /**
     * Operation chercherPersonnes
     */
    public Gestion_acces.personne[] chercherPersonnes(String nom, String prenom);

}

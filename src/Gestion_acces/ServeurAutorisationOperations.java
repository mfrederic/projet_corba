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
    public boolean demanderAutor(Gestion_acces.personne p, short zone)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue;

    /**
     * Operation ajouterAutorisation
     */
    public void ajouterAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue;

    /**
     * Operation modifierAutorisation
     */
    public void modifierAutorisation(Gestion_acces.personne p, short oldZone, Gestion_acces.structPlage oldPlage, short newZone, Gestion_acces.structPlage newPlage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;

    /**
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;

    /**
     * Operation getZonesResp
     */
    public short[] getZonesResp(Gestion_acces.personne resp);

}

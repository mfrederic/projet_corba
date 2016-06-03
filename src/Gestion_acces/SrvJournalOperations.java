package Gestion_acces;

/**
 * Interface definition : SrvJournal
 * 
 * @author OpenORB Compiler
 */
public interface SrvJournalOperations
{
    /**
     * Operation journaliser
     */
    public short journaliser(String timestamp, String typeAcces, Gestion_acces.personne p, boolean resultat, String commentaire);

}

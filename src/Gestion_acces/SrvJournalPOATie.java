package Gestion_acces;

/**
 * Interface definition : SrvJournal
 * 
 * @author OpenORB Compiler
 */
public class SrvJournalPOATie extends SrvJournalPOA
{

    //
    // Private reference to implementation object
    //
    private SrvJournalOperations _tie;

    //
    // Private reference to POA
    //
    private org.omg.PortableServer.POA _poa;

    /**
     * Constructor
     */
    public SrvJournalPOATie(SrvJournalOperations tieObject)
    {
        _tie = tieObject;
    }

    /**
     * Constructor
     */
    public SrvJournalPOATie(SrvJournalOperations tieObject, org.omg.PortableServer.POA poa)
    {
        _tie = tieObject;
        _poa = poa;
    }

    /**
     * Get the delegate
     */
    public SrvJournalOperations _delegate()
    {
        return _tie;
    }

    /**
     * Set the delegate
     */
    public void _delegate(SrvJournalOperations delegate_)
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
     * Operation journaliser
     */
    public void journaliser(String timestamp, String typeAcces, Gestion_acces.personne p, boolean resultat, String commentaire)
    {
        _tie.journaliser( timestamp,  typeAcces,  p,  resultat,  commentaire);
    }

}

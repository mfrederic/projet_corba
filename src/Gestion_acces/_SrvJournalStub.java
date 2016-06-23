package Gestion_acces;

/**
 * Interface definition : SrvJournal
 * 
 * @author OpenORB Compiler
 */
public class _SrvJournalStub extends org.omg.CORBA.portable.ObjectImpl
        implements SrvJournal
{
    static final String[] _ids_list =
    {
        "IDL:Gestion_acces/SrvJournal:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = Gestion_acces.SrvJournalOperations.class;

    /**
     * Operation journaliser
     */
    public void journaliser(String timestamp, String typeAcces, Gestion_acces.personne p, boolean resultat, String commentaire)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("journaliser",false);
                    _output.write_string(timestamp);
                    _output.write_string(typeAcces);
                    Gestion_acces.personneHelper.write(_output,p);
                    _output.write_boolean(resultat);
                    _output.write_string(commentaire);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("journaliser",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.SrvJournalOperations _self = (Gestion_acces.SrvJournalOperations) _so.servant;
                try
                {
                    _self.journaliser( timestamp,  typeAcces,  p,  resultat,  commentaire);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getLogs
     */
    public Gestion_acces.log[] getLogs()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getLogs",true);
                    _input = this._invoke(_output);
                    Gestion_acces.log[] _arg_ret = Gestion_acces.listeLogsHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getLogs",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.SrvJournalOperations _self = (Gestion_acces.SrvJournalOperations) _so.servant;
                try
                {
                    return _self.getLogs();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}

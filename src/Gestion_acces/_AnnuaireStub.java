package Gestion_acces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public class _AnnuaireStub extends org.omg.CORBA.portable.ObjectImpl
        implements Annuaire
{
    static final String[] _ids_list =
    {
        "IDL:Gestion_acces/Annuaire:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = Gestion_acces.AnnuaireOperations.class;

    /**
     * Operation identifier
     */
    public Gestion_acces.personne identifier(short id)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("identifier",true);
                    _output.write_short(id);
                    _input = this._invoke(_output);
                    Gestion_acces.personne _arg_ret = Gestion_acces.personneHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(Gestion_acces.AnnuairePackage.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.AnnuairePackage.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("identifier",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.identifier( id);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation demanderIdentite
     */
    public Gestion_acces.personne demanderIdentite(String ph)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demanderIdentite",true);
                    Gestion_acces.photoHelper.write(_output,ph);
                    _input = this._invoke(_output);
                    Gestion_acces.personne _arg_ret = Gestion_acces.personneHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(Gestion_acces.AnnuairePackage.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.AnnuairePackage.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demanderIdentite",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.demanderIdentite( ph);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation creerPersonne
     */
    public short creerPersonne(String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("creerPersonne",true);
                    _output.write_string(nom);
                    _output.write_string(prenom);
                    Gestion_acces.statutPersonneHelper.write(_output,statut);
                    Gestion_acces.rolePersonneHelper.write(_output,role);
                    _input = this._invoke(_output);
                    short _arg_ret = _input.read_short();
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("creerPersonne",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.creerPersonne( nom,  prenom,  statut,  role);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ajouterPhoto
     */
    public void ajouterPhoto(short idPersonne, String ph)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajouterPhoto",true);
                    _output.write_short(idPersonne);
                    Gestion_acces.photoHelper.write(_output,ph);
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
                    if (_exception_id.equals(Gestion_acces.AnnuairePackage.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.AnnuairePackage.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajouterPhoto",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.ajouterPhoto( idPersonne,  ph);
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
     * Operation modifierInfos
     */
    public void modifierInfos(short idPersonne, String nom, String prenom, Gestion_acces.statutPersonne statut, Gestion_acces.rolePersonne role)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modifierInfos",true);
                    _output.write_short(idPersonne);
                    _output.write_string(nom);
                    _output.write_string(prenom);
                    Gestion_acces.statutPersonneHelper.write(_output,statut);
                    Gestion_acces.rolePersonneHelper.write(_output,role);
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
                    if (_exception_id.equals(Gestion_acces.AnnuairePackage.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.AnnuairePackage.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modifierInfos",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.modifierInfos( idPersonne,  nom,  prenom,  statut,  role);
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
     * Operation supprimerPersonne
     */
    public void supprimerPersonne(short idPersonne)
        throws Gestion_acces.AnnuairePackage.personneInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("supprimerPersonne",true);
                    _output.write_short(idPersonne);
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
                    if (_exception_id.equals(Gestion_acces.AnnuairePackage.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.AnnuairePackage.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("supprimerPersonne",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    _self.supprimerPersonne( idPersonne);
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
     * Operation chercherPersonnes
     */
    public Gestion_acces.personne[] chercherPersonnes(String nom, String prenom)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("chercherPersonnes",true);
                    _output.write_string(nom);
                    _output.write_string(prenom);
                    _input = this._invoke(_output);
                    Gestion_acces.personne[] _arg_ret = Gestion_acces.listePersonnesHelper.read(_input);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("chercherPersonnes",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.AnnuaireOperations _self = (Gestion_acces.AnnuaireOperations) _so.servant;
                try
                {
                    return _self.chercherPersonnes( nom,  prenom);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}

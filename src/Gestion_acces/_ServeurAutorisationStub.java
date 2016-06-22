package Gestion_acces;

/**
 * Interface definition : ServeurAutorisation
 * 
 * @author OpenORB Compiler
 */
public class _ServeurAutorisationStub extends org.omg.CORBA.portable.ObjectImpl
        implements ServeurAutorisation
{
    static final String[] _ids_list =
    {
        "IDL:Gestion_acces/ServeurAutorisation:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = Gestion_acces.ServeurAutorisationOperations.class;

    /**
     * Operation demanderAutor
     */
    public boolean demanderAutor(Gestion_acces.personne p, short idPorte, String date)
        throws Gestion_acces.ServeurAutorisationPackage.porteInconnue
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demanderAutor",true);
                    Gestion_acces.personneHelper.write(_output,p);
                    _output.write_short(idPorte);
                    _output.write_string(date);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.porteInconnueHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.porteInconnueHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demanderAutor",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    return _self.demanderAutor( p,  idPorte,  date);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ajouterAutorisation
     */
    public void ajouterAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
        throws Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.plageIncoherente
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajouterAutorisation",true);
                    Gestion_acces.personneHelper.write(_output,p);
                    _output.write_short(zone);
                    Gestion_acces.structPlageHelper.write(_output,plage);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.plageIncoherenteHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.plageIncoherenteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajouterAutorisation",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    _self.ajouterAutorisation( p,  zone,  plage);
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
     * Operation modifierAutorisation
     */
    public void modifierAutorisation(short numAutor, Gestion_acces.structPlage newPlage)
        throws Gestion_acces.ServeurAutorisationPackage.autorisationInexistante, Gestion_acces.ServeurAutorisationPackage.plageIncoherente
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modifierAutorisation",true);
                    _output.write_short(numAutor);
                    Gestion_acces.structPlageHelper.write(_output,newPlage);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.plageIncoherenteHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.plageIncoherenteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modifierAutorisation",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    _self.modifierAutorisation( numAutor,  newPlage);
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
     * Operation supprimerAutorisation
     */
    public void supprimerAutorisation(short numAutor)
        throws Gestion_acces.ServeurAutorisationPackage.autorisationInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("supprimerAutorisation",true);
                    _output.write_short(numAutor);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("supprimerAutorisation",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    _self.supprimerAutorisation( numAutor);
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
     * Operation getAutorisationsResp
     */
    public Gestion_acces.autorisation[] getAutorisationsResp(short[] zones)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getAutorisationsResp",true);
                    Gestion_acces.listeIdZonesHelper.write(_output,zones);
                    _input = this._invoke(_output);
                    Gestion_acces.autorisation[] _arg_ret = Gestion_acces.listeAutorisationsHelper.read(_input);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getAutorisationsResp",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    return _self.getAutorisationsResp( zones);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getZonesResp
     */
    public short[] getZonesResp(Gestion_acces.personne resp)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getZonesResp",true);
                    Gestion_acces.personneHelper.write(_output,resp);
                    _input = this._invoke(_output);
                    short[] _arg_ret = Gestion_acces.listeIdZonesHelper.read(_input);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getZonesResp",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    return _self.getZonesResp( resp);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getPortes
     */
    public Gestion_acces.porte[] getPortes()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getPortes",true);
                    _input = this._invoke(_output);
                    Gestion_acces.porte[] _arg_ret = Gestion_acces.listePortesHelper.read(_input);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getPortes",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    return _self.getPortes();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}

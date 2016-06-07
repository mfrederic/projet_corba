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
    public boolean demanderAutor(Gestion_acces.personne p, short zone)
        throws Gestion_acces.personneInexistante, Gestion_acces.ServeurAutorisationPackage.zoneInconnue
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
                    _output.write_short(zone);
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
                    if (_exception_id.equals(Gestion_acces.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.read(_exception.getInputStream());
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
                    return _self.demanderAutor( p,  zone);
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
        throws Gestion_acces.personneInexistante, Gestion_acces.ServeurAutorisationPackage.zoneInconnue
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
                    if (_exception_id.equals(Gestion_acces.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.read(_exception.getInputStream());
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
    public void modifierAutorisation(Gestion_acces.personne p, short oldZone, Gestion_acces.structPlage oldPlage, short newZone, Gestion_acces.structPlage newPlage)
        throws Gestion_acces.personneInexistante, Gestion_acces.ServeurAutorisationPackage.zoneInconnue, Gestion_acces.ServeurAutorisationPackage.autorisationInexistante
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modifierAutorisation",true);
                    Gestion_acces.personneHelper.write(_output,p);
                    _output.write_short(oldZone);
                    Gestion_acces.structPlageHelper.write(_output,oldPlage);
                    _output.write_short(newZone);
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
                    if (_exception_id.equals(Gestion_acces.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.personneInexistanteHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.id()))
                    {
                        throw Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.read(_exception.getInputStream());
                    }

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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modifierAutorisation",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAutorisationOperations _self = (Gestion_acces.ServeurAutorisationOperations) _so.servant;
                try
                {
                    _self.modifierAutorisation( p,  oldZone,  oldPlage,  newZone,  newPlage);
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
    public void supprimerAutorisation(Gestion_acces.personne p, short zone, Gestion_acces.structPlage plage)
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
                    _self.supprimerAutorisation( p,  zone,  plage);
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
     * Operation getZonesResp
     */
    public short[] getZonesResp(Gestion_acces.personne resp)
        throws Gestion_acces.personneInexistante
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
                    short[] _arg_ret = Gestion_acces.listeZonesHelper.read(_input);
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    if (_exception_id.equals(Gestion_acces.personneInexistanteHelper.id()))
                    {
                        throw Gestion_acces.personneInexistanteHelper.read(_exception.getInputStream());
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

}

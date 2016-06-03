package Gestion_acces;

/**
 * Interface definition : ServeurAuthentification
 * 
 * @author OpenORB Compiler
 */
public class _ServeurAuthentificationStub extends org.omg.CORBA.portable.ObjectImpl
        implements ServeurAuthentification
{
    static final String[] _ids_list =
    {
        "IDL:Gestion_acces/ServeurAuthentification:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = Gestion_acces.ServeurAuthentificationOperations.class;

    /**
     * Operation demanderAuth
     */
    public Gestion_acces.personne demanderAuth(String emp, String ph, String mdp)
        throws Gestion_acces.accesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("demanderAuth",true);
                    Gestion_acces.empreinteHelper.write(_output,emp);
                    Gestion_acces.photoHelper.write(_output,ph);
                    _output.write_string(mdp);
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
                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("demanderAuth",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    return _self.demanderAuth( emp,  ph,  mdp);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation authentifier
     */
    public Gestion_acces.personne authentifier(String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.droitsInsuffisants, Gestion_acces.accesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("authentifier",true);
                    _output.write_string(user);
                    _output.write_string(password);
                    _output.write_string(mdp);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.droitsInsuffisantsHelper.id()))
                    {
                        throw Gestion_acces.droitsInsuffisantsHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("authentifier",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    return _self.authentifier( user,  password,  mdp);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation ajouterEmpreinte
     */
    public void ajouterEmpreinte(String user, String emp, String mdp)
        throws Gestion_acces.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("ajouterEmpreinte",true);
                    _output.write_string(user);
                    Gestion_acces.empreinteHelper.write(_output,emp);
                    _output.write_string(mdp);
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
                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("ajouterEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    _self.ajouterEmpreinte( user,  emp,  mdp);
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
     * Operation supprimerEmpreinte
     */
    public void supprimerEmpreinte(String user, String mdp)
        throws Gestion_acces.accesRefuse, Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("supprimerEmpreinte",true);
                    _output.write_string(user);
                    _output.write_string(mdp);
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
                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("supprimerEmpreinte",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    _self.supprimerEmpreinte( user,  mdp);
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
     * Operation creerCompte
     */
    public void creerCompte(short idPersonne, String user, String password, String mdp)
        throws Gestion_acces.ServeurAuthentificationPackage.compteDejaCree, Gestion_acces.accesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("creerCompte",true);
                    _output.write_short(idPersonne);
                    _output.write_string(user);
                    _output.write_string(password);
                    _output.write_string(mdp);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.compteDejaCreeHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.compteDejaCreeHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("creerCompte",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    _self.creerCompte( idPersonne,  user,  password,  mdp);
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
     * Operation modifierMdp
     */
    public void modifierMdp(String user, String newMdp, String mdpServeur)
        throws Gestion_acces.ServeurAuthentificationPackage.compteInexistant, Gestion_acces.accesRefuse
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("modifierMdp",true);
                    _output.write_string(user);
                    _output.write_string(newMdp);
                    _output.write_string(mdpServeur);
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
                    if (_exception_id.equals(Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.id()))
                    {
                        throw Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.read(_exception.getInputStream());
                    }

                    if (_exception_id.equals(Gestion_acces.accesRefuseHelper.id()))
                    {
                        throw Gestion_acces.accesRefuseHelper.read(_exception.getInputStream());
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("modifierMdp",_opsClass);
                if (_so == null)
                   continue;
                Gestion_acces.ServeurAuthentificationOperations _self = (Gestion_acces.ServeurAuthentificationOperations) _so.servant;
                try
                {
                    _self.modifierMdp( user,  newMdp,  mdpServeur);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}

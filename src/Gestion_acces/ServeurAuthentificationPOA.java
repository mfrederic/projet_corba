package Gestion_acces;

/**
 * Interface definition : ServeurAuthentification
 * 
 * @author OpenORB Compiler
 */
public abstract class ServeurAuthentificationPOA extends org.omg.PortableServer.Servant
        implements ServeurAuthentificationOperations, org.omg.CORBA.portable.InvokeHandler
{
    public ServeurAuthentification _this()
    {
        return ServeurAuthentificationHelper.narrow(_this_object());
    }

    public ServeurAuthentification _this(org.omg.CORBA.ORB orb)
    {
        return ServeurAuthentificationHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:Gestion_acces/ServeurAuthentification:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("ajouterEmpreinte")) {
                return _invoke_ajouterEmpreinte(_is, handler);
        } else if (opName.equals("authentifier")) {
                return _invoke_authentifier(_is, handler);
        } else if (opName.equals("creerCompte")) {
                return _invoke_creerCompte(_is, handler);
        } else if (opName.equals("demanderAuth")) {
                return _invoke_demanderAuth(_is, handler);
        } else if (opName.equals("modifierEmpreinte")) {
                return _invoke_modifierEmpreinte(_is, handler);
        } else if (opName.equals("modifierMdp")) {
                return _invoke_modifierMdp(_is, handler);
        } else if (opName.equals("supprimerEmpreinte")) {
                return _invoke_supprimerEmpreinte(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_demanderAuth(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = Gestion_acces.empreinteHelper.read(_is);
        String arg1_in = Gestion_acces.photoHelper.read(_is);
        String arg2_in = _is.read_string();

        try
        {
            Gestion_acces.personne _arg_result = demanderAuth(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            Gestion_acces.personneHelper.write(_output,_arg_result);

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_authentifier(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        try
        {
            Gestion_acces.personne _arg_result = authentifier(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            Gestion_acces.personneHelper.write(_output,_arg_result);

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteInexistant _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisantsHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ajouterEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = Gestion_acces.empreinteHelper.read(_is);
        String arg2_in = _is.read_string();

        try
        {
            ajouterEmpreinte(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteInexistant _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.empreinteDejaExistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.empreinteDejaExistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modifierEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = Gestion_acces.empreinteHelper.read(_is);
        String arg2_in = _is.read_string();

        try
        {
            modifierEmpreinte(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisantsHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteInexistant _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_supprimerEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();

        try
        {
            supprimerEmpreinte(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteInexistant _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.suppressionInterditeHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_creerCompte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();

        try
        {
            creerCompte(arg0_in, arg1_in, arg2_in, arg3_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteDejaCree _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteDejaCreeHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modifierMdp(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        try
        {
            modifierMdp(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAuthentificationPackage.compteInexistant _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.compteInexistantHelper.write(_output,_exception);
        }
        catch (Gestion_acces.ServeurAuthentificationPackage.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAuthentificationPackage.accesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

}

package Gestion_acces;

/**
 * Interface definition : ServeurAutorisation
 * 
 * @author OpenORB Compiler
 */
public abstract class ServeurAutorisationPOA extends org.omg.PortableServer.Servant
        implements ServeurAutorisationOperations, org.omg.CORBA.portable.InvokeHandler
{
    public ServeurAutorisation _this()
    {
        return ServeurAutorisationHelper.narrow(_this_object());
    }

    public ServeurAutorisation _this(org.omg.CORBA.ORB orb)
    {
        return ServeurAutorisationHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:Gestion_acces/ServeurAutorisation:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("ajouterAutorisation")) {
                return _invoke_ajouterAutorisation(_is, handler);
        } else if (opName.equals("demanderAutor")) {
                return _invoke_demanderAutor(_is, handler);
        } else if (opName.equals("getAutorisationsResp")) {
                return _invoke_getAutorisationsResp(_is, handler);
        } else if (opName.equals("getZonesResp")) {
                return _invoke_getZonesResp(_is, handler);
        } else if (opName.equals("modifierAutorisation")) {
                return _invoke_modifierAutorisation(_is, handler);
        } else if (opName.equals("supprimerAutorisation")) {
                return _invoke_supprimerAutorisation(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_demanderAutor(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        Gestion_acces.personne arg0_in = Gestion_acces.personneHelper.read(_is);
        short arg1_in = _is.read_short();

        try
        {
            boolean _arg_result = demanderAutor(arg0_in, arg1_in);

            _output = handler.createReply();
            _output.write_boolean(_arg_result);

        }
        catch (Gestion_acces.ServeurAutorisationPackage.porteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAutorisationPackage.porteInconnueHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ajouterAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        Gestion_acces.personne arg0_in = Gestion_acces.personneHelper.read(_is);
        short arg1_in = _is.read_short();
        Gestion_acces.structPlage arg2_in = Gestion_acces.structPlageHelper.read(_is);

        try
        {
            ajouterAutorisation(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAutorisationPackage.zoneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAutorisationPackage.zoneInconnueHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modifierAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();
        Gestion_acces.structPlage arg1_in = Gestion_acces.structPlageHelper.read(_is);

        try
        {
            modifierAutorisation(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAutorisationPackage.autorisationInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_supprimerAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();

        try
        {
            supprimerAutorisation(arg0_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.ServeurAutorisationPackage.autorisationInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.ServeurAutorisationPackage.autorisationInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_getAutorisationsResp(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short[] arg0_in = Gestion_acces.listeIdZonesHelper.read(_is);

        Gestion_acces.autorisation[] _arg_result = getAutorisationsResp(arg0_in);

        _output = handler.createReply();
        Gestion_acces.listeAutorisationsHelper.write(_output,_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_getZonesResp(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        Gestion_acces.personne arg0_in = Gestion_acces.personneHelper.read(_is);

        short[] _arg_result = getZonesResp(arg0_in);

        _output = handler.createReply();
        Gestion_acces.listeIdZonesHelper.write(_output,_arg_result);

        return _output;
    }

}

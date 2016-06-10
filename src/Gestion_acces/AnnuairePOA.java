package Gestion_acces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public abstract class AnnuairePOA extends org.omg.PortableServer.Servant
        implements AnnuaireOperations, org.omg.CORBA.portable.InvokeHandler
{
    public Annuaire _this()
    {
        return AnnuaireHelper.narrow(_this_object());
    }

    public Annuaire _this(org.omg.CORBA.ORB orb)
    {
        return AnnuaireHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:Gestion_acces/Annuaire:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("ajouterPhoto")) {
                return _invoke_ajouterPhoto(_is, handler);
        } else if (opName.equals("chercherPersonnes")) {
                return _invoke_chercherPersonnes(_is, handler);
        } else if (opName.equals("creerPersonne")) {
                return _invoke_creerPersonne(_is, handler);
        } else if (opName.equals("demanderIdentite")) {
                return _invoke_demanderIdentite(_is, handler);
        } else if (opName.equals("identifier")) {
                return _invoke_identifier(_is, handler);
        } else if (opName.equals("modifierInfos")) {
                return _invoke_modifierInfos(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_identifier(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();

        try
        {
            Gestion_acces.personne _arg_result = identifier(arg0_in);

            _output = handler.createReply();
            Gestion_acces.personneHelper.write(_output,_arg_result);

        }
        catch (Gestion_acces.AnnuairePackage.personneInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.AnnuairePackage.personneInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_demanderIdentite(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = Gestion_acces.photoHelper.read(_is);

        try
        {
            Gestion_acces.personne _arg_result = demanderIdentite(arg0_in);

            _output = handler.createReply();
            Gestion_acces.personneHelper.write(_output,_arg_result);

        }
        catch (Gestion_acces.AnnuairePackage.personneInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.AnnuairePackage.personneInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_creerPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        Gestion_acces.statutPersonne arg2_in = Gestion_acces.statutPersonneHelper.read(_is);
        Gestion_acces.rolePersonne arg3_in = Gestion_acces.rolePersonneHelper.read(_is);

        short _arg_result = creerPersonne(arg0_in, arg1_in, arg2_in, arg3_in);

        _output = handler.createReply();
        _output.write_short(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ajouterPhoto(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();
        String arg1_in = Gestion_acces.photoHelper.read(_is);

        try
        {
            ajouterPhoto(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.AnnuairePackage.personneInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.AnnuairePackage.personneInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modifierInfos(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        Gestion_acces.statutPersonne arg3_in = Gestion_acces.statutPersonneHelper.read(_is);
        Gestion_acces.rolePersonne arg4_in = Gestion_acces.rolePersonneHelper.read(_is);

        try
        {
            modifierInfos(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in);

            _output = handler.createReply();

        }
        catch (Gestion_acces.AnnuairePackage.personneInexistante _exception)
        {
            _output = handler.createExceptionReply();
            Gestion_acces.AnnuairePackage.personneInexistanteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_chercherPersonnes(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();

        Gestion_acces.personne[] _arg_result = chercherPersonnes(arg0_in, arg1_in);

        _output = handler.createReply();
        Gestion_acces.listePersonnesHelper.write(_output,_arg_result);

        return _output;
    }

}

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

    private static final java.util.Map operationMap = new java.util.HashMap();

    static {
            operationMap.put("ajouterEmpreinte",
                    new Operation_ajouterEmpreinte());
            operationMap.put("authentifier",
                    new Operation_authentifier());
            operationMap.put("creerCompte",
                    new Operation_creerCompte());
            operationMap.put("demanderAuth",
                    new Operation_demanderAuth());
            operationMap.put("getComptes",
                    new Operation_getComptes());
            operationMap.put("modifierEmpreinte",
                    new Operation_modifierEmpreinte());
            operationMap.put("modifierMdp",
                    new Operation_modifierMdp());
            operationMap.put("supprimerCompte",
                    new Operation_supprimerCompte());
            operationMap.put("supprimerEmpreinte",
                    new Operation_supprimerEmpreinte());
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        final AbstractOperation operation = (AbstractOperation)operationMap.get(opName);

        if (null == operation) {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }

        return operation.invoke(this, _is, handler);
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
        short arg0_in = _is.read_short();
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

    private org.omg.CORBA.portable.OutputStream _invoke_supprimerCompte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        short arg0_in = _is.read_short();
        String arg1_in = _is.read_string();

        try
        {
            supprimerCompte(arg0_in, arg1_in);

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

    private org.omg.CORBA.portable.OutputStream _invoke_getComptes(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        Gestion_acces.compte[] _arg_result = getComptes();

        _output = handler.createReply();
        Gestion_acces.listeComptesHelper.write(_output,_arg_result);

        return _output;
    }

    // operation classes
    private abstract static class AbstractOperation {
        protected abstract org.omg.CORBA.portable.OutputStream invoke(
                ServeurAuthentificationPOA target,
                org.omg.CORBA.portable.InputStream _is,
                org.omg.CORBA.portable.ResponseHandler handler);
    }

    private static final class Operation_demanderAuth extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_demanderAuth(_is, handler);
        }
    }

    private static final class Operation_authentifier extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_authentifier(_is, handler);
        }
    }

    private static final class Operation_ajouterEmpreinte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_ajouterEmpreinte(_is, handler);
        }
    }

    private static final class Operation_modifierEmpreinte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_modifierEmpreinte(_is, handler);
        }
    }

    private static final class Operation_supprimerEmpreinte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_supprimerEmpreinte(_is, handler);
        }
    }

    private static final class Operation_creerCompte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_creerCompte(_is, handler);
        }
    }

    private static final class Operation_supprimerCompte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_supprimerCompte(_is, handler);
        }
    }

    private static final class Operation_modifierMdp extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_modifierMdp(_is, handler);
        }
    }

    private static final class Operation_getComptes extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final ServeurAuthentificationPOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_getComptes(_is, handler);
        }
    }

}

package Gestion_acces;

/**
 * Interface definition : SrvJournal
 * 
 * @author OpenORB Compiler
 */
public abstract class SrvJournalPOA extends org.omg.PortableServer.Servant
        implements SrvJournalOperations, org.omg.CORBA.portable.InvokeHandler
{
    public SrvJournal _this()
    {
        return SrvJournalHelper.narrow(_this_object());
    }

    public SrvJournal _this(org.omg.CORBA.ORB orb)
    {
        return SrvJournalHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:Gestion_acces/SrvJournal:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("journaliser")) {
                return _invoke_journaliser(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_journaliser(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        Gestion_acces.personne arg2_in = Gestion_acces.personneHelper.read(_is);
        boolean arg3_in = _is.read_boolean();
        String arg4_in = _is.read_string();

        journaliser(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in);

        _output = handler.createReply();

        return _output;
    }

}

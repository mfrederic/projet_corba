package interfaces.responsablezone;

import java.lang.reflect.MalformedParametersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDate {

	private static String _jour;
	private static String _mois;
	private static String _annee;
	
	public static void analyseStringDate(String value) throws MalformedParametersException {
		String[] splitted = value.split("/");
		if(splitted.length < 3)
			throw new MalformedParametersException("La date (jour) doit etre au format 'dd/MM/yy'.");
		MatcherDate._jour = splitted[0];
		MatcherDate._mois = splitted[1];
		MatcherDate._annee = splitted[2];
	}
	
	public static boolean testDateIsValid(String value) throws MalformedParametersException {
		MatcherDate.analyseStringDate(value);
		
		if(MatcherDate.jourIsValid(MatcherDate._jour)
				&& MatcherDate.moisIsValid(MatcherDate._mois)
				&& MatcherDate.anneeIsValid(MatcherDate._annee))
			return true;
        
        return false;
	}
	
	public static boolean jourIsValid(String jour) throws MalformedParametersException {
		MatcherDate._jour = jour;
		Pattern pattern = Pattern.compile("[0-9]{2}");
        Matcher matcher = pattern.matcher(MatcherDate._jour);
        if(!matcher.find())
        	throw new MalformedParametersException("Le jour doit etre au format 'dd'.");

        int day = Integer.valueOf(MatcherDate._jour);
        if(day >= 1 && day <= 31)
        	return true;
        else
        	throw new MalformedParametersException("Le jour doit etre compris entre 01 et 31.");
	}
	
	public static boolean moisIsValid(String mois) throws MalformedParametersException {
		MatcherDate._mois = mois;
		Pattern pattern = Pattern.compile("[0-9]{2}");
        Matcher matcher = pattern.matcher(MatcherDate._mois);
        if(!matcher.find())
        	throw new MalformedParametersException("Le mois doit etre au format 'mm'.");

        int month = Integer.valueOf(MatcherDate._mois);
        if(month >= 1 && month <= 12)
        	return true;
        else
        	throw new MalformedParametersException("Le mois doit etre compris entre 01 et 12.");
	}
	
	public static boolean anneeIsValid(String annee) throws MalformedParametersException {
		MatcherDate._annee = annee;
		Pattern pattern = Pattern.compile("[0-9]{2}");
        Matcher matcher = pattern.matcher(MatcherDate._annee);
        if(!matcher.find())
        	throw new MalformedParametersException("L'annee doit etre au format 'yy'");

        int year = Integer.valueOf(MatcherDate._annee);
        if(year >= 0 && year <= 99)
        	return true;
        else
        	throw new MalformedParametersException("L'annee doit etre comprise entre 00 et 99.");
	}

	public static String get_jour() {
		return _jour;
	}

	public static String get_mois() {
		return _mois;
	}

	public static String get_annee() {
		return _annee;
	}
	
}

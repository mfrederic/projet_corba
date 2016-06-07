package log;

import model.Journal;
import Gestion_acces.SrvJournalPOA;
import Gestion_acces.personne;
import bdd.objetsdao.JournalDAO;

public class JournalImpl extends SrvJournalPOA{

	private JournalDAO repoJournal;
	
	@Override
	public short journaliser(String timestamp, String typeAcces, personne p,
			boolean resultat, String commentaire) {
		// TODO Auto-generated method stub
		Journal j = new Journal();
		j.setCommentaire(commentaire);
		j.setRefPersonne(p.idPers);
		j.setResultat(resultat);
		j.setTypeAcces(typeAcces);
		j.setTimestamp(timestamp);
		System.out.println("test");
		j = repoJournal.create(j);
		System.out.println("test2");
		if (j.getIdJournal() == 0)
			return -1;
		else
			return 0;		
	}

}

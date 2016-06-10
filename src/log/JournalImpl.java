package log;

import model.Journal;
import Gestion_acces.SrvJournalPOA;
import Gestion_acces.personne;
import bdd.objetsdao.JournalDAO;

public class JournalImpl extends SrvJournalPOA{

	private JournalDAO repoJournal;
	
	public JournalImpl() {
		repoJournal = new JournalDAO();
	}
	
	@Override
	public void journaliser(String timestamp, String typeAcces, personne p,
			boolean resultat, String commentaire) {
		// TODO Auto-generated method stub
		Journal j = new Journal();
		j.setCommentaire(commentaire);
		j.setRefPersonne(p.idPers);
		j.setResultat(resultat);
		j.setTypeAcces(typeAcces);
		j.setTimestamp(timestamp);
		
		j = repoJournal.create(j);

	}

}

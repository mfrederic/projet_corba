package log;

import java.util.ArrayList;

import model.Journal;
import Gestion_acces.SrvJournalPOA;
import Gestion_acces.log;
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

	@Override
	public log[] getLogs() {
		// TODO Auto-generated method stub
		ArrayList<Journal> listeLogs;
		log[] listeLogsORB = null;
		
		listeLogs = repoJournal.getAllLog();
	
		if (listeLogs == null)
			listeLogsORB = new log[0];		
		else {
			Journal[] listeLogsBD = new Journal[listeLogs.size()];
			listeLogsBD = (Journal[]) listeLogs.toArray(listeLogsBD);
			listeLogsORB = new log[listeLogsBD.length];
			for (int i=0; i<listeLogsBD.length; i++) {
				listeLogsORB[i] = new log((short)listeLogsBD[i].getIdJournal(), listeLogsBD[i].getTimestamp(), listeLogsBD[i].getTypeAcces(), (short)listeLogsBD[i].getRefPersonne(), listeLogsBD[i].isResultat(), listeLogsBD[i].getCommentaire());
			}
		}
		return listeLogsORB;
	}
	
	

}

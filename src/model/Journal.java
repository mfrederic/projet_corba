package model;

import java.util.Date;

public class Journal {
	
	private String timestamp;
	private String typeAcces;
	private int refPersonne;
	private boolean resultat;
	private String commentaire;
	private int idJournal;
	
	public Journal(String p_typeAcces, int p_refPersonne, boolean p_resulat, String p_commentaire, int p_idJournal) {
		Date thisTime = new Date();
		this.timestamp = String.valueOf(thisTime.getTime());
		this.typeAcces = p_typeAcces;
		this.refPersonne = p_refPersonne;
		this.resultat = p_resulat;
		this.commentaire = p_commentaire;
		this.setIdJournal(p_idJournal);
	}
	
	public Journal() {
	}

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTypeAcces() {
		return typeAcces;
	}
	public void setTypeAcces(String typeAcces) {
		this.typeAcces = typeAcces;
	}
	public int getRefPersonne() {
		return refPersonne;
	}
	public void setRefPersonne(int refPersonne) {
		this.refPersonne = refPersonne;
	}
	public boolean isResultat() {
		return resultat;
	}
	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Override
	public String toString(){
		return 	"timestamp: " + getTimestamp() + "\n" 
		+		"type acces: " + getTypeAcces() + "\n" 
		+		"refPersonne: " + getRefPersonne() + "\n"
		+		"resultat: " + isResultat() + "\n"
		+		"commentaire: " + getCommentaire() + "\n"
		+		"identifiant: " + getIdJournal();
		
	}

	public int getIdJournal() {
		return idJournal;
	}

	public void setIdJournal(int idJournal) {
		this.idJournal = idJournal;
	}
	
}

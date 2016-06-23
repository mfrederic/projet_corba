package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bdd.DAO;
import model.Autorisation;
import model.Compte;
import model.Journal;

public class JournalDAO extends DAO<Journal>{

	@Override
	public Journal find(int idTimestamp) {
		Journal ligneLog = new Journal();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"JournalAcces\" WHERE \"idJournal\" = " + idTimestamp);
			if (result.first()) {
				ligneLog.setTimestamp(result.getString(1));
				ligneLog.setTypeAcces(result.getString(2));
				ligneLog.setRefPersonne(result.getInt(3));
				ligneLog.setResultat(result.getBoolean(4));
				if(result.getString(5)==null)
					ligneLog.setCommentaire(result.getString(5));
				else
					ligneLog.setCommentaire(new String());
				ligneLog.setIdJournal(result.getInt(6));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ligneLog;
	}

	@Override
	public Journal create(Journal obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"JournalAcces\" (\"refPersonne\",\"typeAcces\",\"timestamp\",\"resultat\",\"commentaire\") VALUES(?, ?, ?,?,?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getRefPersonne());
			prepare.setString(2, obj.getTypeAcces());		
			prepare.setString(3, obj.getTimestamp());
			prepare.setBoolean(4, obj.isResultat());
			if(!obj.getCommentaire().equals(""))
				prepare.setString(5, obj.getCommentaire());	
			else
				prepare.setString(5, null);	
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(6));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Journal update(Journal obj) {
		Journal out = find(obj.getIdJournal());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE \"JournalAcces\" SET (\"refPersonne\",\"typeAcces\",\"timestamp\",\"resultat\",\"commentaire\") =(?, ?, ?,?,?)" +
									" where \"idJournal\"="+ obj.getIdJournal(),
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1,obj.getRefPersonne());
			prepare.setString(2, obj.getTypeAcces());
			prepare.setString(3, obj.getTimestamp());
			prepare.setBoolean(4, obj.isResultat());
			prepare.setString(5, obj.getCommentaire());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public void delete(Journal obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"JournalAcces\" WHERE \"idJournal\" = " +obj.getIdJournal());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<Journal> getAllLog(){
		ArrayList<Journal> pers = new ArrayList<Journal>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"JournalAcces\"");
			while (result.next()) {
				Journal a = this.find(result.getInt(6));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

	@Override
	public ArrayList<Journal> getInstances() {
		ArrayList<Journal> pers = new ArrayList<Journal>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"JournalAcces\"");
			while (result.next()) {
				Journal a = this.find(result.getInt(6));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

}
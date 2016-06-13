package bdd.objetsdao;

import helpers.MaPlageDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Autorisation;
import bdd.DAO;

public class AutorisationDAO extends DAO<Autorisation>{

	@Override
	public Autorisation find(int id) {
		Autorisation autor = new Autorisation();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Autorisation\" WHERE \"numAuto\" = " + id);
			if (result.first()) {
				autor.setNumAuto(result.getInt(1));
				autor.setRefPersonne(result.getInt(2));
				autor.setRefZone(result.getInt(3));
				autor.setHeureDebut(result.getFloat(4));
				autor.setHeureFin(result.getFloat(5));
				if(result.getString(6)==null)
					autor.setJourDebut(new String());
				else
					autor.setJourDebut(result.getString(6));
				if(result.getString(7)==null)
					autor.setJourFin(new String());
				else
					autor.setJourFin(result.getString(7));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autor;
	}

	@Override
	public Autorisation create(Autorisation obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"Autorisation\" (\"refPersonne\",\"refZone\",\"heureDebut\",\"heureFin\",\"jourDebut\",\"jourFin\") VALUES(?, ?, ?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getRefPersonne());
			prepare.setInt(2, obj.getRefZone());		
			prepare.setFloat(3, obj.getHeureDebut());
			prepare.setFloat(4, obj.getHeureFin());
			if(obj.getJourDebut().equals(""))
				prepare.setString(5, null);
			else 
				prepare.setString(5, obj.getJourDebut());
			
			if(obj.getJourFin().equals(""))
				prepare.setString(6, null);
			else 
				prepare.setString(6, obj.getJourFin());
			
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(4));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Autorisation update(Autorisation obj) {
		Autorisation out = find(obj.getNumAuto());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE  \"Autorisation\"  SET (\"numAuto\",\"refPersonne\",\"refZone\",\"heureDebut\",\"heureFin\",\"jourDebut\",\"jourFin\") = (?, ?, ?,?,?,?,?)"+
									" where \"numAuto\"="+ obj.getNumAuto(),
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getNumAuto());
			prepare.setInt(2, obj.getRefPersonne());		
			prepare.setInt(3, obj.getRefZone());
			prepare.setFloat(4, obj.getHeureDebut());
			prepare.setFloat(5, obj.getHeureFin());
			if(obj.getJourDebut().equals(""))
				prepare.setString(6, null);
			else
				prepare.setString(6, obj.getJourDebut());
			if(obj.getJourFin().equals(""))
				prepare.setString(7, null);
			else
				prepare.setString(7, obj.getJourFin());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public void delete(Autorisation obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"Autorisation\" WHERE \"numAuto\" = " +obj.getNumAuto());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//retourne la liste d'autorisation pour une personne ou null si aucun match
	public List<Autorisation> findAllByPersonne(int idPersonne) {
		List<Autorisation> out = new ArrayList<Autorisation>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Autorisation\" WHERE \"refPersonne\" = " + idPersonne);
			while (result.next()) {
				out.add(find(result.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(out.size()>0)
			return out;
		return null;
	}
	
	public ArrayList<Autorisation> findAllByZones(ArrayList<Integer> idZones) {
		ArrayList<Autorisation> out = new ArrayList<Autorisation>();
		String requete = "SELECT * FROM \"Autorisation\"";
		try {
			Iterator<Integer> it = idZones.iterator();
			int i = 0;
			while (it.hasNext()) {
				if (i == 0)
					requete += " WHERE \"refZone\" = '" + it.next() + "'";
				else
					requete += " OR \"refZone\" = '" + it.next() + "'";
			}

			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(requete);
			while (result.next()) {
				out.add(find(result.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(out.size()>0)
			return out;
		return null;
	}
	
	//retourne la liste de plages d'autorisation pour une personne et une zone ou null si aucun match
	public List<MaPlageDate> findAllByPersonneZone(int idPersonne, int idZone) {
		List<MaPlageDate> out = new ArrayList<MaPlageDate>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Autorisation\" WHERE \"refPersonne\" = " + idPersonne + " AND \"refZone\" = " + idZone);
			while (result.next()) {
				out.add(new MaPlageDate(result.getString(6), result.getString(7), result.getInt(4), result.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(out.size()>0)
			return out;
		return null;
	}

	@Override
	public ArrayList<Autorisation> getInstances() {
		ArrayList<Autorisation> pers = new ArrayList<Autorisation>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Autorisation\"");
			while (result.next()) {
				Autorisation a = this.find(result.getInt(1));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

}
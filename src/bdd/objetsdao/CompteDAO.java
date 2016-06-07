package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.DAO;
import model.Compte;

public class CompteDAO extends DAO<Compte>{

	@Override
	public Compte find(int id) {
		Compte compt = new Compte();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Compte\" WHERE \"refPersonne\" = " + id);
			if (result.first()) {
				compt.setUser(result.getString(1));
				compt.setPassword(result.getString(2));
				compt.setEmpreinte(result.getString(3));
				compt.setRefPersonne(result.getInt(4));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compt;
	}

	@Override
	public Compte create(Compte obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"Compte\" (\"refPersonne\",\"user\",\"password\",empreinte) VALUES(?, ?, ?,?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getRefPersonne());
			prepare.setString(2, obj.getUser());		
			prepare.setString(3, obj.getPassword());
			prepare.setString(4, obj.getEmpreinte());
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
	public Compte update(Compte obj) {
		Compte out = find(obj.getRefPersonne());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE  \"Compte\"  SET (\"refPersonne\",\"user\",\"password\",\"empreinte\") = (?, ?, ?,?)"+
									" where \"refPersonne\"="+ obj.getRefPersonne(),
							Statement.RETURN_GENERATED_KEYS
							);
		
			
			
			prepare.setInt(1, obj.getRefPersonne());
			prepare.setString(2, obj.getUser());		
			prepare.setString(3, obj.getPassword());
			prepare.setString(4, obj.getEmpreinte());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public void delete(Compte obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"Compte\" WHERE \"refPersonne\" = " +obj.getRefPersonne());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//retourne le compte ou null si empreinte inconnu
	public Compte findByEmpreinte(String empreinte) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Compte\" WHERE \"empreinte\" = " + empreinte);
			if (result.first()) {
				return find(result.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//retourne le compte ou null si empreinte inconnu
	public Compte findByUserAndPassword(String user, String password) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM \"Compte\" WHERE \"user\" = " + user + " AND \"password\" = " + password);
				
			if (result.first()) {
				return find(result.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	


	@Override
	public ArrayList<Compte> getInstances() {
		ArrayList<Compte> pers = new ArrayList<Compte>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Compte\"");
			while (result.next()) {
				Compte a = this.find(result.getInt(1));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

}
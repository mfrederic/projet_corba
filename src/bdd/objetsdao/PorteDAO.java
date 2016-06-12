package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.DAO;
import model.Compte;
import model.Porte;

public class PorteDAO extends DAO<Porte>{

	@Override
	public Porte find(int id) {
		Porte port = new Porte();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Porte\" WHERE \"idPorte\" = " + id);
			if (result.first()) {
				port.setRefZone(result.getInt(1));
				port.setIdPorte(result.getInt(2));
				port.setLibellePorte(result.getString(3));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return port;
	}

	@Override
	public Porte create(Porte obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"Porte\" (\"refZone\",\"libellePorte\") VALUES( ?, ?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setInt(1, obj.getRefZone());
			prepare.setString(3, obj.getLibellePorte());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(2));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Porte update(Porte obj) {
		Porte out = find(obj.getIdPorte());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE  \"Porte\"  SET (\"refZone\",\"idPorte\",\"libellePorte\") = (?, ?, ?)"+
									" where \"refPersonne\"="+ obj.getIdPorte(),
							Statement.RETURN_GENERATED_KEYS
							);
		
			prepare.setInt(1, obj.getRefZone());
			prepare.setInt(2, obj.getIdPorte());
			prepare.setString(3, obj.getLibellePorte());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public void delete(Porte obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"Porte\" WHERE \"idPorte\" = " +obj.getIdPorte());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Porte> getInstances() {
		ArrayList<Porte> pers = new ArrayList<Porte>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Porte\"");
			while (result.next()) {
				Porte a = this.find(result.getInt(2));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}
	
	
	public ArrayList<Porte> getAllPorteByZone(int idZone) {
		ArrayList<Porte> pers = new ArrayList<Porte>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Porte\" WHERE \"refZone\" =" + idZone);
			while (result.next()) {
				Porte a = this.find(result.getInt(2));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

}
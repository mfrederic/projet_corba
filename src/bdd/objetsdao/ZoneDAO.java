package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.DAO;
import model.Compte;
import model.Porte;
import model.Zone;

public class ZoneDAO extends DAO<Zone>{

	@Override
	public Zone find(int id) {
		Zone port = new Zone();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Zone\" WHERE \"idZone\" = " + id);
			if (result.first()) {
				port.setLibelleZone(result.getString(1));
				port.setRespZone(result.getInt(2));
				port.setIdZone(result.getInt(3));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return port;
	}

	@Override
	public Zone create(Zone obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"Zone\" (\"libelleZone\",\"respZone\") VALUES(?, ?)",
							Statement.RETURN_GENERATED_KEYS
							);
			prepare.setString(1, obj.getLibelleZone());
			prepare.setInt(2, obj.getRespZone());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(3));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Zone update(Zone obj) {
		Zone out = find(obj.getIdZone());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE  \"Zone\"  SET (\"libelleZone\",\"respZone\",\"idZone\") = (?, ?, ?)"+
									" where \"idZone\"="+ obj.getIdZone(),
							Statement.RETURN_GENERATED_KEYS
							);
		
			prepare.setString(1, obj.getLibelleZone());
			prepare.setInt(2, obj.getRespZone());
			prepare.setInt(3, obj.getIdZone());
			prepare.executeUpdate();
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			return find(rs.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public void delete(Zone obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"Zone\" WHERE \"idZone\" = " +obj.getIdZone());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Zone> getInstances() {
		ArrayList<Zone> pers = new ArrayList<Zone>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Zone\"");
			while (result.next()) {
				Zone a = this.find(result.getInt(3));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}
	
	public ArrayList<Integer> getZoneByRespZone(int id){
		
		ArrayList<Integer> zones = new ArrayList<Integer>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Zone\" where \"respZone\"="+id);
			while (result.next()) {
				zones.add(result.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zones;
	}
	
	

}
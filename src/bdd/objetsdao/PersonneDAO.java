package bdd.objetsdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bdd.DAO;
import model.Compte;
import model.Personne;

public class PersonneDAO extends DAO<Personne>{

	@Override
	public Personne find(int id) {
		Personne pers = new Personne();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM \"Personne\" WHERE \"idPersonne\" = " + id);
			if (result.first()) {
				if(result.getString(1)==null)
					pers.setNomPersonne(new String());
				else
					pers.setNomPersonne(result.getString(1));
				if(result.getString(2)==null)
					pers.setPrenomPersonne(new String());
				else
					pers.setPrenomPersonne(result.getString(2));

				if(result.getString(3)==null)
					pers.setPhotoPersonne(new String());
				else 
					pers.setPhotoPersonne(result.getString(3));
				if(result.getString(4)== null)
					pers.setStatutPersonne(new String());
				else
					pers.setStatutPersonne(result.getString(4));
				if(result.getString(5)==null)
					pers.setRolePersonne(new String());
				else 
					pers.setRolePersonne(result.getString(5));

				pers.setIdPersonne(result.getInt(6));
			}
			else return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

	@Override
	public Personne create(Personne obj) {
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"INSERT INTO \"Personne\" (\"nomPersonne\",\"prenomPersonne\",\"photoPersonne\",\"statutPersonne\",\"rolePersonne\") VALUES(?, ?, ?,?,?)",
							Statement.RETURN_GENERATED_KEYS
							);
			if(obj.getNomPersonne().equals("") )
				prepare.setString(1, null);
			else
				prepare.setString(1, obj.getNomPersonne());

			if(obj.getPrenomPersonne().equals("") )
				prepare.setString(2, null);
			else
				prepare.setString(2, obj.getPrenomPersonne());	


			if(obj.getPhotoPersonne().equals("") )
				prepare.setString(3, null);
			else
				prepare.setString(3, obj.getPhotoPersonne());	

			if(obj.getStatutPersonne().equals("") )
				prepare.setString(4, null);
			else
				prepare.setString(4, obj.getStatutPersonne());	

			if(obj.getRolePersonne().equals("") )
				prepare.setString(5, null);
			else
				prepare.setString(5, obj.getRolePersonne());	

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
	public Personne update(Personne obj) {
		Personne out = find(obj.getIdPersonne());
		try {
			PreparedStatement prepare = 
					this.connect.prepareStatement(
							"UPDATE  \"Personne\"  SET (\"nomPersonne\",\"prenomPersonne\",\"photoPersonne\",\"statutPersonne\",\"rolePersonne\") = (?, ?, ?,?,?)"+
									" where \"idPersonne\"="+ obj.getIdPersonne(),
									Statement.RETURN_GENERATED_KEYS
							);
			if(obj.getNomPersonne().equals(""))
				prepare.setString(1, null);
			else
				prepare.setString(1, obj.getNomPersonne());
			if(obj.getPrenomPersonne().equals(""))
				prepare.setString(2, null);		
			else
				prepare.setString(2, obj.getPrenomPersonne());		
			if(obj.getPhotoPersonne().equals(""))
				prepare.setString(3, null);
			else 
				prepare.setString(3, obj.getPhotoPersonne());
			if(obj.getStatutPersonne().equals(""))
				prepare.setString(4, null);
			else 
				prepare.setString(4, obj.getStatutPersonne());
			if(obj.getRolePersonne().equals(""))
				prepare.setString(5, null);
			else 
				prepare.setString(5, obj.getRolePersonne());
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
	public void delete(Personne obj) {
		try{
			Statement stmt = connect.createStatement();
			stmt.execute("DELETE FROM \"Personne\" WHERE \"idPersonne\" = " +obj.getIdPersonne());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Personne> getInstances() {
		ArrayList<Personne> pers = new ArrayList<Personne>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Personne\"");
			while (result.next()) {
				
				Personne a = this.find(result.getInt("idPersonne"));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}

	//retourne la liste de personnes ou null si inconnu
	public ArrayList<Personne> getByNomPrenom(String nom, String prenom) {
		ArrayList<Personne> pers = new ArrayList<Personne>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM \"Personne\" WHERE \"nomPersonne\" = '" + nom + "' AND \"prenomPersonne\" = '" + prenom + "'");
			while (result.next()) {
				Personne a = this.find(result.getInt(1));
				pers.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pers;
	}

	//retourne la personne ou null si photo inconnue
	public Personne findByPicture(String photo) {
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM \"Personne\" WHERE \"photoPersonne\" = '" + photo + "'");

			if (result.first()) {
				return find(result.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
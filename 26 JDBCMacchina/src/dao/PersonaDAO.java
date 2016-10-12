package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import model.Persona;

public class PersonaDAO {
	
	private Connection con = ConnesioneDAO.connetti();

	public Persona aggiungiPersona(String nome, String cognome, String codF) {
		
		if (cercaPersona(codF) == null) {

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String sql = "INSERT INTO PERSONA(nome, cognome, codF) " + 
						     "VALUES (?, ?, ?)";

				ps = con.prepareStatement(sql, new String[] { "Nome", "Cognome", "CodF" });
				ps.setString(1, nome);
				ps.setString(2, cognome);
				ps.setString(3, codF);

				ps.executeQuery();
				rs = ps.getGeneratedKeys();

				if (rs.next()) {
					
					if ((nome.compareTo(rs.getString(1)) == 0) && 
							(cognome.compareTo(rs.getString(2)) == 0) &&
							(codF.compareTo(rs.getString(3)) == 0) ) {

						return new Persona(rs.getString(1), rs.getString(2), rs.getString(3));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return null;
	}

	public Persona cercaPersona(String codF) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Nome,Cognome,CodF " + 
						 "FROM Persona " + 
					     "WHERE CodF = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, codF);

			rs = ps.executeQuery();
			if (rs.next()) {
				return new Persona(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	public boolean cancellaPersona(String codF) {
		
		if (cercaPersona(codF) != null) {

			PreparedStatement ps = null;
			try {

				String sql = "DELETE FROM PERSONA " + 
						     "WHERE CodF = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, codF);

				ps.executeQuery();

				if (cercaPersona(codF) == null) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return false;
	}

	public Persona modificaPersona(String nomeNew, String cognomeNew, String codF) {
		
		if (cercaPersona(codF) != null) {
			
			PreparedStatement ps = null;
			try {

				String sql = "UPDATE PERSONA " + 
							 "SET Nome = ?," +
							 "Cognome = ? " +
							 "WHERE CodF = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, nomeNew);
				ps.setString(2, cognomeNew);
				ps.setString(3, codF);

				ps.executeQuery();
				
				Persona p = cercaPersona(codF);
				
				if ((p != null) && (nomeNew.compareTo(p.getNome()) == 0) && 
								   (cognomeNew.compareTo(p.getCognome()) == 0) &&
								   (codF.compareTo(p.getCodF()) == 0)) {
					
					return p;
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return null;
	}

	public Map<String, Persona> getTuttePersone() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT Cognome, Nome, CodF" + 
					  	 "FROM Persona " + 
					  	 "ORDER BY Cognome";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			Map<String, Persona> temp = new TreeMap<String, Persona>();
			while (rs.next()) {

				temp.put(rs.getString(2), new Persona(rs.getString(2), rs.getString(1),
													rs.getString(3)));
			}
			if (!temp.isEmpty()) {
				return temp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

}

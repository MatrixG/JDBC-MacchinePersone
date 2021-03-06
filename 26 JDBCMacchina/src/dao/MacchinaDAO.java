package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import model.Macchina;

public class MacchinaDAO extends ConnesioneDAO {

	private Connection con = ConnesioneDAO.connetti();

	// Questo metodo cerca se la macchina non � gi� presente
	// e, se non lo �, la aggiunge al db
	public Macchina aggiungiMacchina(String modello, String targa) {

		if (cercaMacchina(targa) == null) {

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String sql = "INSERT INTO MACCHINA(modello, targa) " + 
						  	 "VALUES (?, ?)";

				ps = con.prepareStatement(sql, new String[] { "Modello", "Targa" });
				ps.setString(1, modello);
				ps.setString(2, targa);

				ps.executeQuery();
				rs = ps.getGeneratedKeys();

				if (rs.next()) {

					if ((modello.compareTo(rs.getString(1)) == 0) && (targa.compareTo(rs.getString(2)) == 0)) {

						return new Macchina(rs.getString(1), rs.getString(2));
					}
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
		}
		return null;
	}

	public Macchina cercaMacchina(String targa) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Modello, Targa " + 
						 "FROM Macchina " + 
						 "WHERE Targa = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, targa);

			rs = ps.executeQuery();
			if (rs.next()) {
				return new Macchina(rs.getString(1), rs.getString(2));
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

	// Cerca che la macchina esista e poi la cancella
	public boolean cancellaMacchina(String targa) {

		if (cercaMacchina(targa) != null) {

			PreparedStatement ps = null;
			try {

				String sql = "DELETE FROM MACCHINA " + 
							 "WHERE TARGA = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, targa);

				ps.executeQuery();

				if (cercaMacchina(targa) == null) {
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

	// Modifica una macchina se esiste nel DB
	public Macchina modificaMacchina(String targa, String nomeNew) {

		if (cercaMacchina(targa) != null) {

			PreparedStatement ps = null;
			try {

				String sql = "UPDATE MACCHINA " + 
							 "SET Modello = ? " + 
							 "WHERE Targa = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, nomeNew);
				ps.setString(2, targa);

				ps.executeQuery();

				Macchina m = cercaMacchina(targa);

				if ((m != null) && (nomeNew.compareTo(m.getModello()) == 0) && (targa.compareTo(m.getTarga()) == 0)) {

					return m;
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
		return null;
	}

	// Questo metodo ritorna tutte le macchine presenti nel DB
	public Map<String, Macchina> getTutteMacchine() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT Modello, Targa " + 
						 "FROM Macchina " + 
						 "ORDER BY Modello";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			Map<String, Macchina> temp = new TreeMap<String, Macchina>();
			while (rs.next()) {

				temp.put(rs.getString(2), new Macchina(rs.getString(1), rs.getString(2)));
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

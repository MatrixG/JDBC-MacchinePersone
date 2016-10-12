package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Macchina;

public class MacchinaDAO extends ConnesioneDAO {

	private Connection con = ConnesioneDAO.connetti();

	public Macchina aggiungiMacchina(String modello, String targa) {

		if (cercaMacchina(targa) == null) {

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String sql = "INSERT INTO MACCHINA(modello, targa) " + "VALUES (?, ?)";

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

	public Macchina cercaMacchina(String targa) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Modello, Targa " + "FROM Macchina " + "WHERE Targa = ?";

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

	public boolean cancellaMacchina(String targa) {

		if (cercaMacchina(targa) != null) {

			PreparedStatement ps = null;
			try {

				String sql = "DELETE FROM MACCHINA " + "WHERE TARGA = ?";

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

	public Macchina modificaMacchina(String targa, String nomeNew) {

		if (cercaMacchina(targa) != null) {
			
			PreparedStatement ps = null;
			try {

				String sql = "UPDATE MACCHINA " + "SET Modello = ? " + "WHERE Targa = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, nomeNew);
				ps.setString(2, targa);

				ps.executeQuery();
				
				Macchina m = cercaMacchina(targa);
				
				if ((m != null) && (nomeNew.compareTo(m.getModello()) == 0) && 
								   (targa.compareTo(m.getTarga()) == 0)) {
					
					return m;
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
}

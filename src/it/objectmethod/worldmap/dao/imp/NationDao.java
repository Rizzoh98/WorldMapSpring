package it.objectmethod.worldmap.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.worldmap.config.ConnectionDB;
import it.objectmethod.worldmap.dao.INationDao;
import it.objectmethod.worldmap.domain.Nation;

public class NationDao implements INationDao {

	@Override
	public List<Nation> getAllNation(String continent) {
		List<Nation> nations = new ArrayList<Nation>();

		Nation nation = null;

		Connection connession = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = "SELECT DISTINCT Name,Code FROM country WHERE Continent = ?";
			stmt = connession.prepareStatement(sql);
			stmt.setString(1, continent);
			result = stmt.executeQuery();

			while (result.next()) {

				nation = new Nation();
				nation.setName(result.getString("Name"));
				nation.setCountrycode(result.getString("Code"));
				nations.add(nation);

			}
			result.close();
			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (result != null)
					result.close();

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

		return nations;
	}
	
	@Override
	public List<Nation> getAllNations() {
		List<Nation> allnations = new ArrayList<Nation>();

		Nation nation = null;

		Connection connession = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = "SELECT DISTINCT Name,Code FROM country";
			stmt = connession.prepareStatement(sql);;
			result = stmt.executeQuery();

			while (result.next()) {

				nation = new Nation();
				nation.setName(result.getString("Name"));
				nation.setCountrycode(result.getString("Code"));
				allnations.add(nation);

			}
			result.close();
			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (result != null)
					result.close();

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

		return allnations;
	}

	@Override
	public List<String> getAllContinent() {
		List<String> continents = new ArrayList<String>();

		Connection connession = null;
		Statement stmt = null;
		ResultSet result = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = "SELECT DISTINCT Continent FROM country";
			stmt = connession.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				continents.add(result.getString("Continent"));
			}
			result.close();
			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (result != null)
					result.close();

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

		return continents;
	}

}

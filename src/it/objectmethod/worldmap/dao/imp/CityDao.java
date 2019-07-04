package it.objectmethod.worldmap.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import it.objectmethod.worldmap.DBconnection.ConnectionDB;
import it.objectmethod.worldmap.dao.ICityDao;
import it.objectmethod.worldmap.domain.City;

public class CityDao implements ICityDao {

	@Override
	public ArrayList<City> getAllCity(String nation) {
		ArrayList<City> citys = new ArrayList<City>();

		Connection connession = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		City city = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = "SELECT DISTINCT Name, Population, ID FROM city WHERE CountryCode= ?";
			stmt = connession.prepareStatement(sql);
			stmt.setString(1, nation);
			result = stmt.executeQuery();

			while (result.next()) {

				city = new City();
				city.setName(result.getString("Name"));
				city.setPopulation(result.getString("Population"));
				city.setId(result.getInt("ID"));
				citys.add(city);

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

		return citys;
	}

	@Override
	public void deleteCity(int id) {

		Connection connession = null;
		PreparedStatement stmt = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = "DELETE FROM city WHERE ID = ?";
			stmt = connession.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

	}

	@Override
	public void updateCity(int id, String city2) {

		Connection connession = null;
		PreparedStatement stmt = null;

		try {
			connession = ConnectionDB.getConnection();
			String sql = "UPDATE city SET Name=? WHERE ID = ?";
			stmt = connession.prepareStatement(sql);
			stmt.setString(1, city2);
			stmt.setInt(2, id);

			stmt.executeUpdate();

			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

	}

	@Override
	public void addCity(String cityadd, String countrycode) {
		Connection connession = null;
		PreparedStatement stmt = null;
	
		try {
			connession = ConnectionDB.getConnection();
			String sql = "INSERT INTO city (Name,CountryCode) values (?,?)";
			stmt = connession.prepareStatement(sql);
			stmt.setString(1, cityadd);
			stmt.setString(2, countrycode);

			stmt.executeUpdate();

			stmt.close();
			connession.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

			try {

				if (stmt != null)
					stmt.close();

				if (connession != null)
					connession.close();

			} catch (Exception xe) {
				xe.printStackTrace();
			}

		}

	}

	@Override
	public ArrayList<City> orderCity(String nation, String order) {

		ArrayList<City> citys = new ArrayList<City>();

		City city = null;

		Connection connession = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {

			connession = ConnectionDB.getConnection();
			String sql = null;

			sql = "SELECT DISTINCT Name, Population, ID FROM city WHERE CountryCode= ? ORDER BY " + order;

			stmt = connession.prepareStatement(sql);
			stmt.setString(1, nation);
			result = stmt.executeQuery();

			while (result.next()) {

				city = new City();
				city.setName(result.getString("Name"));
				city.setPopulation(result.getString("Population"));
				city.setId(result.getInt("ID"));
				citys.add(city);

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

		return citys;

	}
}

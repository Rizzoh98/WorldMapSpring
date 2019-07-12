package it.objectmethod.worldmap.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.worldmap.config.ConnectionDB;
import it.objectmethod.worldmap.config.Constants;
import it.objectmethod.worldmap.dao.ICityDao;
import it.objectmethod.worldmap.domain.City;

public class CityDao extends NamedParameterJdbcDaoSupport implements ICityDao {	
		
	@Override
	public void deleteCity(int id) {
		
		String sql = "DELETE FROM city WHERE ID = :cityid";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityid", id);
		getNamedParameterJdbcTemplate().update(sql, params);
		
	}

	@Override
	public void updateCity(int id, String city2) {
		
		String sql = "UPDATE city SET Name = :cityid WHERE ID = :city2";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityid", id);
		params.addValue("city2", city2);
		getNamedParameterJdbcTemplate().update(sql, params);
		
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

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT Name, Population, ID FROM city WHERE CountryCode= ? ");

			switch (order) {
			case Constants.ORDER_AZ:
				sb.append("ORDER BY Name ASC");
				break;
			case Constants.ORDER_ZA:
				sb.append("ORDER BY Name DESC");
				break;
			case Constants.ORDER_POP_ASC:
				sb.append("ORDER BY Population ASC");
				break;
			case Constants.ORDER_POP_DESC:
				sb.append("ORDER BY Population DESC");
				break;
			default:
				// DO NOTHING
			}

			String sql = sb.toString();

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
	public City getCityById(Integer id) {
		City city = null;

		Connection connession = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {

			connession = ConnectionDB.getConnection();

			String sql = "SELECT Name, Population, ID FROM city WHERE ID = ?";

			stmt = connession.prepareStatement(sql);
			stmt.setInt(1, id);
			result = stmt.executeQuery();

			while (result.next()) {
				city = new City();
				city.setName(result.getString("Name"));
				city.setPopulation(result.getString("Population"));
				city.setId(result.getInt("ID"));
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

		return city;
	}
}

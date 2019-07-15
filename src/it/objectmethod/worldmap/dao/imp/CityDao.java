package it.objectmethod.worldmap.dao.imp;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

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

		String sql = "UPDATE city SET Name = :city2 WHERE ID = :cityid";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityid", id);
		params.addValue("city2", city2);
		getNamedParameterJdbcTemplate().update(sql, params);

	}

	@Override
	public void addCity(String cityadd, String countrycode) {

		String sql = "INSERT INTO city (Name,CountryCode) values (:cityadd, :countrycode)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityadd", cityadd);
		params.addValue("countrycode", countrycode);
		getNamedParameterJdbcTemplate().update(sql, params);

	}

	@Override
	public City getCityById(Integer id) {

		City city = null;
		String sql = "SELECT Name, Population, ID FROM city WHERE ID = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		city = getNamedParameterJdbcTemplate().queryForObject(sql, params, rm);

		return city;
	}

	@Override
	public List<City> orderCity(String nation, String order) {
  
		List<City> citys = null;


			StringBuilder sb = new StringBuilder();
			sb.append("SELECT Name, Population, ID FROM city WHERE CountryCode= :nation ");

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
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("nation", nation);
			BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
			citys = getNamedParameterJdbcTemplate().query(sql, params, rm);

			return citys;		
	}

}

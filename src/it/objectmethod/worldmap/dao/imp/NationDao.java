package it.objectmethod.worldmap.dao.imp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.worldmap.dao.INationDao;
import it.objectmethod.worldmap.domain.Nation;
import it.objectmethod.worldmap.domain.mapper.NationMapper;

public class NationDao implements INationDao {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Nation> getAllNation(String continent) {
		
		List<Nation> getallnation = null;
		String sql = "SELECT * FROM country WHERE Continent = ?";
		getallnation = this.jdbcTemplateObject.query(sql, new Object[]{continent}, new NationMapper());	
		
		return getallnation;
	}	
	
	@Override
	public List<Nation> getAllNations() {
		
		List<Nation> getallnations = null;
		String sql = "SELECT DISTINCT Name,Code FROM country";
		getallnations = this.jdbcTemplateObject.query(sql, new NationMapper());	
		
		return getallnations;
	}

	
	@Override
	public List<String> getAllContinent() {
		
		List<String> continents = null;
		String sql = "SELECT DISTINCT Continent FROM country";
		continents = this.jdbcTemplateObject.queryForList(sql,String.class);	
		
		return continents;
		
	}

}

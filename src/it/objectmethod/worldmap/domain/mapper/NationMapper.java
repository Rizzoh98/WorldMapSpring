package it.objectmethod.worldmap.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.worldmap.domain.Nation;

public class NationMapper implements RowMapper<Nation>{
	
	@Override
	public Nation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Nation nation = new Nation();
		nation.setCountrycode(rs.getString("Code"));
		nation.setName(rs.getString("Name"));
		return nation;
	}
	
}

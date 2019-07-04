package it.objectmethod.worldmap.dao;


import java.util.List;

import it.objectmethod.worldmap.domain.Nation;

public interface INationDao {
	
	public List<String> getAllContinent();
	public List<Nation> getAllNation(String continent);
	
}

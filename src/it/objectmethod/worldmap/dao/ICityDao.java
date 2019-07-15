package it.objectmethod.worldmap.dao;


import java.util.List;

import it.objectmethod.worldmap.domain.City;

public interface ICityDao {
	
	public City getCityById(Integer id);
	public void deleteCity(int id);
	public void updateCity(int id, String city2);
	public void addCity(String cityadd,String countrycode);
	public List<City> orderCity(String nation,String order);

	
	
}

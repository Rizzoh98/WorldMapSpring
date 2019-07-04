package it.objectmethod.worldmap.dao;

import java.util.ArrayList;

import it.objectmethod.worldmap.domain.City;

public interface ICityDao {
	
	public ArrayList<City> getAllCity(String nation);
	public void deleteCity(int id);
	public void updateCity(int id, String city2);
	public void addCity(String cityadd,String countrycode);
	public ArrayList<City> orderCity(String nation,String order);

	
	
}

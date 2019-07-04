package it.objectmethod.worldmap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.objectmethod.worldmap.dao.imp.CityDao;
import it.objectmethod.worldmap.domain.City;

@Controller
public class CitysController {

	@GetMapping("/citys")
	public String getIndex(ModelMap model, @PathParam("countrycode") String countrycode,
			@PathParam("order") String order, HttpSession session) {
		List<City> citta = new ArrayList<City>();
		CityDao cityDao = new CityDao();
		String orderP = null;
		String order2 = null;
		
		if (countrycode == null) 
			countrycode = (String) session.getAttribute("nation");
		else 
			session.setAttribute("nation", countrycode);
		
		

		if (order == null) {
			order = "Az";
			orderP = "pMin";
		}

		if (order.equals("Az")) {
			order = "Za";
			order2 = "Name ASC";
			orderP = "pMin";
		} else if (order.equals("Za")) {
			order = "Az";
			order2 = "Name DESC";
			orderP = "pMin";
		} else if (order.equals("pMin")) {
			order = "Az";
			orderP = "pMax";
			order2 = "Population ASC";
		} else if (order.equals("pMax")) {
			order = "Az";
			orderP = "pMin";
			order2 = "Population DESC";
		}

		try {
			citta = cityDao.orderCity(countrycode, order2);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		model.addAttribute("order", order);
		model.addAttribute("orderP", orderP);
		model.addAttribute("result", citta);
		return "Citta";
	}

	@GetMapping("/Add")
	public String addCity(@PathParam("countrycode") String countrycode, @PathParam("city") String city, HttpSession session) {
		
		CityDao cityDao = new CityDao();
		
		//if (countrycode == null)
			countrycode = (String) session.getAttribute("nation");
			

		cityDao.addCity(city, countrycode);
		
		
		return "forward:/citys";		
	}

	@GetMapping("/Delete")
	public String deleteCity(@PathParam("id") int id) {
		CityDao cityDao = new CityDao();

		cityDao.deleteCity(id);
		
		return "forward:/citys";
	}

	@GetMapping("/Update")
	public String updateCity(@PathParam("city") int city, @PathParam("countrycode") String countrycode) {
		CityDao cityDao = new CityDao();

		cityDao.updateCity(city, countrycode);
		
		return "forward:/citys";
	}

}

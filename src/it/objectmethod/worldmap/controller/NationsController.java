package it.objectmethod.worldmap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.worldmap.dao.imp.NationDao;
import it.objectmethod.worldmap.domain.Nation;

@Controller
public class NationsController {
	
	@RequestMapping("/")
	public String getIndex(ModelMap model) {

		List<String> continenti = new ArrayList<String>();
		NationDao nationDao = new NationDao();

		try {
			continenti = nationDao.getAllContinent();
		} catch (Exception e) {

			e.printStackTrace();
		}

		model.addAttribute("result", continenti);

		return "Continenti";
	}

	@RequestMapping("/nation")
	public String getIndex(ModelMap model, @RequestParam(value="continent", required = false) String continent, HttpSession session) {
		List<Nation> nazioni = null;

		if (continent == null) {
			continent = (String) session.getAttribute("continent");
		} else {
			session.setAttribute("continent", continent);
		}

		NationDao nationDao = new NationDao();

		try {
			nazioni = nationDao.getAllNation(continent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("result", nazioni);

		return "Nazioni";
	}

}

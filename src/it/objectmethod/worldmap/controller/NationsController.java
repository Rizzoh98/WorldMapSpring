package it.objectmethod.worldmap.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import it.objectmethod.worldmap.dao.imp.NationDao;
import it.objectmethod.worldmap.domain.Nation;

@Controller
public class NationsController {

	@RequestMapping("/nation")
	public String getIndex(ModelMap model, @PathParam("continent") String continent, HttpSession session) {
		List<Nation> nazioni = null;

		if (continent == null)
			continent = (String) session.getAttribute("continent");
		else
			session.setAttribute("continent", continent);

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

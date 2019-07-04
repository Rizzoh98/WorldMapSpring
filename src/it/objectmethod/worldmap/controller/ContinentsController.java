package it.objectmethod.worldmap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



import it.objectmethod.worldmap.dao.imp.NationDao;

@Controller
public class ContinentsController {
	
	@RequestMapping("/")
	public String getIndex(ModelMap model) {
		
		List<String>continenti = new ArrayList<String>();
		NationDao nationDao = new NationDao();
		
		try {
			continenti = nationDao.getAllContinent();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		model.addAttribute("result",continenti);
		
		return "Continenti";
	}
	
}

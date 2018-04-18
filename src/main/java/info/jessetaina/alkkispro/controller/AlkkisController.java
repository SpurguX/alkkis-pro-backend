package info.jessetaina.alkkispro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.jessetaina.alkkispro.model.DrinkRepository;
import info.jessetaina.alkkispro.model.DrinkEntry;
import info.jessetaina.alkkispro.model.DrinkEntryRepository;
import info.jessetaina.alkkispro.model.Drink;

@Controller
public class AlkkisController {
	

	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private DrinkEntryRepository drinkEntryRepository;

	@RequestMapping(value = "/add_drink", method=RequestMethod.POST)
	public @ResponseBody String lisaaPOST(HttpServletRequest request, @RequestParam(value="drink_name") String name, 
			@RequestParam(value="volume") Double volume, @RequestParam(value="alc_content") Double alc_content, @RequestParam(value="units") Double units) {
		Drink newDrink = new Drink(name, volume, alc_content, units);
		drinkRepository.save(newDrink);
		return "Added: " + newDrink;
	}
	
	@GetMapping(path="/all_drinks")
	public @ResponseBody Iterable<Drink> haeKaikkiJuomat() {
		return drinkRepository.findAll();
	}
	
	@GetMapping(path="/all_entries")
	public @ResponseBody Iterable<DrinkEntry> fetchAllEntries() {
		return drinkEntryRepository.findAll();
	}
	
	@GetMapping(path = "/admin_page")
	public String adminsivu() {
		return "admin.html";
	}
}

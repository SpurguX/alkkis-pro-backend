package info.jessetaina.alkkispro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.net.SyslogOutputStream;
import info.jessetaina.alkkispro.model.DrinkEntry;
import info.jessetaina.alkkispro.model.DrinkEntryRepository;
import info.jessetaina.alkkispro.model.DrinkRepository;
import info.jessetaina.alkkispro.model.Drink;

@Controller
public class AlkkisController {
	

	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private DrinkEntryRepository drinkEntryRepository;

	@RequestMapping(value = "/add_drink", method=RequestMethod.POST)
	public @ResponseBody Drink addDrink(HttpServletRequest request, @RequestParam(value="drink_name") String name, 
			@RequestParam(value="volume") Double volume, @RequestParam(value="alc_content") Double alc_content, @RequestParam(value="units") Double units) {
		Drink newDrink = new Drink(name, volume, alc_content, units, true);
		drinkRepository.save(newDrink);
		return newDrink;
	}
	
	@RequestMapping(value = "/save_other_drink", method=RequestMethod.POST)
	public @ResponseBody Drink saveOtherDrink(HttpServletRequest request, @RequestParam(value="drinkName") String name, 
			@RequestParam(value="volume") Double volume, @RequestParam(value="alcContent") Double alc_content, @RequestParam(value="units") Double units) {
		Drink newSavedDrink = new Drink(name, volume, alc_content, units, false);
		drinkRepository.save(newSavedDrink);
		return newSavedDrink;
	}
	
	@GetMapping(path="/all_saved_drinks")
	public @ResponseBody Iterable<Drink> fetchSaveddrinks() {
		return drinkRepository.findByIsDefault(false);
	}
	
	@GetMapping(path="/all_default_drinks")
	public @ResponseBody Iterable<Drink> fetchDefaultDrinks() {
		return drinkRepository.findByIsDefault(true);
	}
	
	@GetMapping(path="/all_entries")
	public @ResponseBody Iterable<DrinkEntry> fetchAllEntries() {
		return drinkEntryRepository.findAll();
	}
	
	@GetMapping(path = "/admin_page")
	public String adminsivu() {
		return "admin.html";
	}
	
	@RequestMapping(value = "/add_entry", method=RequestMethod.POST)
	public @ResponseBody String addEntry(HttpServletRequest request, @RequestBody DrinkEntry[] entries) {
		for (DrinkEntry de : entries ) {
			System.out.println(de.getDrink());
			System.out.println(de.getDrink().getDrinkId());
			de.setDrink(drinkRepository.findById(de.getDrink().getDrinkId()));
			drinkEntryRepository.save(de);
		}	
		return "Added: " + entries;
	}	
	
	@RequestMapping(value = "/delete_entry", method=RequestMethod.POST)
		public @ResponseBody String deleteEntry(HttpServletRequest request, @RequestParam(value="drink_entry_id") String drink_entry_id) {
			long idLong = Long.parseLong(drink_entry_id);
			drinkEntryRepository.deleteById(idLong);	
		return "Deleted: " + drink_entry_id;
	}
	
	@RequestMapping(value = "/edit_entry", method=RequestMethod.POST)
	public @ResponseBody String editEntry(HttpServletRequest request, @RequestBody DrinkEntry entry) {
		drinkEntryRepository.save(entry);
	return "Updated: " + entry;
}
	
}

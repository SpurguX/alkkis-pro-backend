package info.jessetaina.alkkispro.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.JsonNode;

import info.jessetaina.alkkispro.model.DrinkEntry;
import info.jessetaina.alkkispro.model.DrinkEntryRepository;
import info.jessetaina.alkkispro.model.DrinkRepository;
import info.jessetaina.alkkispro.model.Drink;
import info.jessetaina.alkkispro.model.User;
import info.jessetaina.alkkispro.model.UserRepository;

@Controller
public class AlkkisController {

	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private DrinkEntryRepository drinkEntryRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/add_drink", method = RequestMethod.POST)
	public @ResponseBody Drink addDrink(HttpServletRequest request, @RequestParam(value = "drink_name") String name,
			@RequestParam(value = "volume") Double volume, @RequestParam(value = "alc_content") Double alc_content,
			@RequestParam(value = "units") Double units, @RequestParam(value = "type") Drink.DrinkType type) {
		Drink newDrink = new Drink(name, volume, alc_content, units, true, type);
		drinkRepository.save(newDrink);
		return newDrink;
	}

	@RequestMapping(value = "/save_other_drink", method = RequestMethod.POST)
	public @ResponseBody Drink saveOtherDrink(HttpServletRequest request, @RequestParam(value = "drinkName") String name,
			@RequestParam(value = "volume") Double volume, @RequestParam(value = "alcContent") Double alc_content,
			@RequestParam(value = "units") Double units, @RequestParam(value = "type") Drink.DrinkType type) {
		User user = userRepository.findByUsername(request.getRemoteUser());
		Drink newSavedDrink = new Drink(name, volume, alc_content, units, type, user);
		drinkRepository.save(newSavedDrink);
		return newSavedDrink;
	}

	@GetMapping(path = "/all_saved_drinks")
	public @ResponseBody Iterable<Drink> fetchSaveddrinks() {
		return drinkRepository.findByIsDefault(false);
	}

	@GetMapping(path = "/all_default_drinks")
	public @ResponseBody Iterable<Drink> fetchDefaultDrinks() {
		return drinkRepository.findByIsDefault(true);
	}

	@GetMapping(path = "/entry")
	public @ResponseBody Iterable<DrinkEntry> fetchAllEntries(@RequestParam(value = "today_only", defaultValue = "false") Boolean todayOnly) {
		if (todayOnly) {
			Date today = new Date();
			return drinkEntryRepository.findAllByDrinkingDate(today);
		}
		return drinkEntryRepository.findAll();
	}

	@PostMapping(value = "/entry")
	public @ResponseBody String addEntry(HttpServletRequest request, @RequestBody DrinkEntry[] entries) {
		for (DrinkEntry de : entries) {
			User user = userRepository.findByUsername(request.getRemoteUser());
			de.setDrink(drinkRepository.findById(de.getDrink().getDrinkId()));
			de.setUser(user);
			drinkEntryRepository.save(de);
		}
		return "Added: " + entries;
	}

	// TODO see if there are issues (e.g. reg. security or data filtering) with automatically generated endpoints
	// Automatic endpoints take care of this atm.
	// @DeleteMapping(value = "/entry")
	// public @ResponseBody String deleteEntry(HttpServletRequest request,
	// 		@RequestParam(value = "drink_entry_id") String drink_entry_id) {
	// 	long idLong = Long.parseLong(drink_entry_id);
	// 	drinkEntryRepository.deleteById(idLong);
	// 	return "Deleted: " + drink_entry_id;
	// }

	@PutMapping(value = "/edit_entry")
	public @ResponseBody String editEntry(HttpServletRequest request, @RequestBody DrinkEntry entry) {
		// XXX Would PATCH make more sense here? Could avoid having to fetch user.
		User user = userRepository.findByUsername(request.getRemoteUser());
		entry.setUser(user);
		drinkEntryRepository.save(entry);
		return "Updated: " + entry;
	}

	@GetMapping(path = "/users")
	public @ResponseBody Iterable<User> fetchUser() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/create_user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createUser(HttpServletRequest request, @RequestBody User newUser) {
		String currentUser = request.getRemoteUser();
		if (!currentUser.equals("adminjesse")) {
			throw new AccessDeniedException("Only allowed for admin");
		}

		userRepository.save(newUser);
		return newUser.getUsername();
	}

	@PostMapping(value = "/change_password", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addEntry(HttpServletRequest request, @RequestBody JsonNode data) {
		User currentUser = userRepository.findByUsername(request.getRemoteUser());
		JsonNode passwordFieldNode = data.get("password");
		if (
			// The field is either not present in parentNode, or explicitly set to null.
			passwordFieldNode == null ||
			passwordFieldNode.isNull() ||
			!passwordFieldNode.isTextual()
		) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required field 'password' is missing or not valid.");
		}

		String newPassword = passwordFieldNode.asText();

		int minPasswordLength = 8;
		if (newPassword.length() < minPasswordLength) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least " + minPasswordLength + " characters long.");
		}

		currentUser.setPassword(newPassword);
		userRepository.save(currentUser);

		return "OK";
	}

	// Not in use atm. Need to create auth logic for backend to use this.
	@GetMapping(path = "/admin_page")
	public String adminsivu() {
		return "admin.html";
	}
}

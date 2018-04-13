package info.jessetaina.alkkispro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.jessetaina.alkkispro.model.JuomaRepository;
import info.jessetaina.alkkispro.model.Juoma;

@Controller
public class JuomaController {
	

	@Autowired
	private JuomaRepository juomaRepository;
	
	@RequestMapping(value = "/lisaa_juoma", method=RequestMethod.GET)
	public @ResponseBody String lisaa(@RequestParam(value="juoma_nimi") String nimi, @RequestParam(value="tilavuus") Double tilavuus, @RequestParam(value="vahvuus") Double vahvuus, @RequestParam(value="annokset") Double annokset) {
		Juoma uusiJuoma = new Juoma(nimi, tilavuus, vahvuus, annokset);
		juomaRepository.save(uusiJuoma);
		return "Lisättiin: " + uusiJuoma;
	}
	
	@RequestMapping(value = "/lisaa_juoma", method=RequestMethod.POST)
	public @ResponseBody String lisaaPOST(HttpServletRequest request, @RequestParam(value="juoma_nimi") String nimi, @RequestParam(value="tilavuus") Double tilavuus, @RequestParam(value="vahvuus") Double vahvuus, @RequestParam(value="annokset") Double annokset) {
		Juoma uusiJuoma = new Juoma(nimi, tilavuus, vahvuus, annokset);
		juomaRepository.save(uusiJuoma);
		return "Lisättiin: " + uusiJuoma;
	}
	
	@GetMapping(path="/kaikki_juomat")
	public @ResponseBody Iterable<Juoma> haeKaikkiJuomat() {
		return juomaRepository.findAll();
	}
	
	@GetMapping(path = "/admin_sivu")
	public String adminsivu() {
		return "admin.html";
	}
}

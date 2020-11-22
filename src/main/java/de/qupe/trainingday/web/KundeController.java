package de.qupe.trainingday.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import de.qupe.trainingday.db.KundeRepository;
import de.qupe.trainingday.domain.Kunde;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class KundeController {

	@Autowired
	protected KundeRepository kundeRepository;

	public KundeController(MeterRegistry registry) {
	}

	@GetMapping(path = "kunden")
	public String kundenliste(Model model) {

		List<Kunde> kunden = kundeRepository.findAll();
		model.addAttribute("kunden", kunden);
		return "kunden";
	}

	@GetMapping(path = "kunde/{kundennummer}")
	public String kunde(Model model, @PathVariable(name = "kundennummer", required = true) int kundennummer) {
		Kunde kunde = kundeRepository.find(kundennummer);
		return showKunde(model, kunde);
	}

	@GetMapping(path = "kundensuche")
	public String suche(Model model, @RequestParam(required = true, name = "vorname") String vorname,
			@RequestParam(required = true, name = "nachname") String nachname) {

		List<Kunde> kunden = kundeRepository.find(nachname, vorname);

		if (kunden.size() == 1) {
			return showKunde(model, kunden.get(0));
		}

		model.addAttribute("kunden", kunden);
		return "kunden";
	}

	private String showKunde(Model model, Kunde kunde) {
		model.addAttribute("kunde", kunde);
		return "kunde";
	}
}

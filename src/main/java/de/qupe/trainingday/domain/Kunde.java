package de.qupe.trainingday.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Kunde {
	// ausnahmsweise public!

	@Id
	public Integer kundennummer;
	public String vorname;
	public String nachname;
	public LocalDate geburtsdatum;

	public Kunde(Integer kundennummer, String vorname, String nachname, LocalDate geburtsdatum) {
		super();
		this.kundennummer = kundennummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}

}

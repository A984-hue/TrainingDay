package de.qupe.trainingday.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import de.qupe.trainingday.db.KundeRepository;
import de.qupe.trainingday.domain.Kunde;

class KundeControllerTest {
	
	// sut = system under test
	private KundeController sut;
	private KundeRepository kundeRepository;

	@BeforeEach
	void setUp() throws Exception {
		this.kundeRepository = mock(KundeRepository.class);
		this.sut = new KundeController(null);
		this.sut.kundeRepository = this.kundeRepository;
	}

	@Test
	void testSucheMehrereSuchtreffer() {

		// given
		Kunde k1 = new Kunde(1, "Willi", "Meier", LocalDate.of(1975, 1, 1));
		Kunde k2 = new Kunde(2, "Schmidt", "Peter", LocalDate.of(1975, 1, 1));
		List<Kunde> kunden = new ArrayList<>();
		kunden.add(k1);
		kunden.add(k2);
		String suchbegriffVorname = "egal";
		String suchbegriffNachname = "auch egal";
		Model model = mock(Model.class);
		
		// when
		when(this.kundeRepository.find(eq(suchbegriffNachname), eq(suchbegriffVorname))).thenReturn(kunden);
	
		String view = this.sut.suche(model, suchbegriffVorname, suchbegriffNachname);
		
		// then
		assertEquals("kunden", view);
		verify(this.kundeRepository, never()).findAll();
		verify(this.kundeRepository, times(1)).find(eq(suchbegriffNachname), eq(suchbegriffVorname));
	
	
	}

	@Test
	void testSucheGenauEinSuchtreffer() {
		//given
		Kunde k1 = new Kunde(1, "Willi", "Meier", LocalDate.of(1975, 1, 1));
		List<Kunde> kunden = new ArrayList<>();
		kunden.add(k1);
		String suchbegriffVorname = "egal";
		String suchbegriffNachname = "auch egal";
		Model model = mock(Model.class);
		//when
		when(this.kundeRepository.find(eq(suchbegriffNachname), eq(suchbegriffVorname))).thenReturn(kunden);
	
		String view = this.sut.suche(model, suchbegriffVorname, suchbegriffNachname);
		//then
		assertEquals("kunde", view);
		
		verify(this.kundeRepository, never()).findAll();
		verify(this.kundeRepository, times(1)).find(eq(suchbegriffNachname), eq(suchbegriffVorname));
	}

	@Test
	void testAnzeigeEinesKunden() {
		//given
		Kunde k1 = new Kunde(1, "Willi", "Meier", LocalDate.of(1975, 1, 1));
		Model model = mock(Model.class);
		//when
		when(this.kundeRepository.find(eq(k1.kundennummer))).thenReturn(k1);
		String view = this.sut.kunde(model, k1.kundennummer);
		//then
		assertEquals("kunde", view);
		verify(this.kundeRepository, never()).findAll();
		verify(this.kundeRepository, times(1)).find(eq(k1.kundennummer));
	}
	@Test
	void testAnzeigeDerKundenliste() {
		//given
		List<Kunde> kunden = Collections.emptyList();
		Model model = mock(Model.class);
		//when
		when(this.kundeRepository.findAll()).thenReturn(kunden);
		String view = this.sut.kundenliste(model);
		//then
		assertEquals("kunden", view);
		verify(this.kundeRepository, never()).find(any());
		verify(this.kundeRepository, times(1)).findAll();
	}
}

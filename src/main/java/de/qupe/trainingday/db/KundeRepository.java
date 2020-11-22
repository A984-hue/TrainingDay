package de.qupe.trainingday.db;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import de.qupe.trainingday.domain.Kunde;

public interface KundeRepository extends Repository<Kunde, Integer> {

	@Query("SELECT kundennummer, nachname, vorname, geburtsdatum FROM public.kunden")
	List<Kunde> findAll();

	@Query("SELECT kundennummer, nachname, vorname, geburtsdatum FROM public.kunden WHERE kundennummer=:kundennummer")
	Kunde find(@Param("kundennummer") Integer kundennummer);

	@Query("SELECT kundennummer, nachname, vorname, geburtsdatum FROM public.kunden where upper(nachname) like '%' || upper(:nachname) || '%' and upper(vorname) like '%' || upper(:vorname) || '%'")
	List<Kunde> find(@Param("nachname") String nachname, @Param("vorname") String vorname);	
	
}

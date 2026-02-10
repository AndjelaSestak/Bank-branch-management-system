package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;

@Service
public interface UslugaService extends CrudService<Usluga> {

	List<Usluga> getUslugasByProvizijaLessThan(double provizija);
	
	List<Usluga> findByForeignKey(Filijala filijala);
	List<Usluga> findByForeignKey(KorisnikUsluge korisnik);
}

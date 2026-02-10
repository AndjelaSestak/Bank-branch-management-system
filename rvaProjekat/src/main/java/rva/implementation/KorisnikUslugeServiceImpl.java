package rva.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.KorisnikUsluge;
import rva.models.Usluga;
import rva.repository.KorisnikUslugeRepository;
import rva.services.KorisnikUslugeService;
@Component
public class KorisnikUslugeServiceImpl implements KorisnikUslugeService {

	@Autowired
	private KorisnikUslugeRepository repo;
	
	@Override
	public List<KorisnikUsluge> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}
	
	@Override
	public Optional<KorisnikUsluge> findById(int id)
	{
		return repo.findById(id);
	}

	@Override
	public KorisnikUsluge create(KorisnikUsluge t) {
		return repo.save(t);
	}

	@Override
	public Optional<KorisnikUsluge> update(KorisnikUsluge t, int id) {
		if(existsById(id)) {
			t.setID(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);

	}

	@Override
	public List<KorisnikUsluge> getKorisniksByIme(String ime) {
		return repo.findByImeContainingIgnoreCase(ime);
	}

}

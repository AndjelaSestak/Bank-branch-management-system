package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Banka;
import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.services.BankaService;
import rva.services.FilijalaService;
import rva.services.KorisnikUslugeService;
@CrossOrigin
@RestController
public class KorisnikUslugeController {

	
	@Autowired
	private KorisnikUslugeService korisnikUslugeService;
	
	@GetMapping("/korisnikusluge")
	//GetMapping je skracena verzija RequestMappinga
	//@RequestMapping(method=RequestMethod.GET, path= "/banka")
	public List<KorisnikUsluge>getAllKorisniks()
	{
		return korisnikUslugeService.getAll();
	}
	
	@GetMapping("/korisnikusluge/id/{id}")
	public ResponseEntity<?>getKorisnikUslugeById(@PathVariable int id)
	{
		Optional<KorisnikUsluge> korisnikUsluge = korisnikUslugeService.findById(id);
		if(korisnikUsluge.isPresent()) {
			return ResponseEntity.ok(korisnikUsluge.get());
		}
		return ResponseEntity.status(404).body("Resource with requeted ID: " + id + "does not exist");
	}
	
	@GetMapping("/korisnikusluge/ime/{ime}")
	public ResponseEntity<?> getKorisniksByNaziv(@PathVariable String ime){
		List<KorisnikUsluge> korisniciUsluge = korisnikUslugeService.getKorisniksByIme(ime);
		if(korisniciUsluge.isEmpty()) {
				ResponseEntity.status(404).body("Resources with ime " + ime + "do not exist");
		}
		return ResponseEntity.ok(korisniciUsluge);
	}
	
	@PostMapping("/korisnikusluge")
	public ResponseEntity<?> createKorisnikUsluge(@RequestBody KorisnikUsluge korisnikUsluge){
		if(korisnikUslugeService.existsById(korisnikUsluge.getID())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		KorisnikUsluge savedKorisnikUsluge = korisnikUslugeService.create(korisnikUsluge);
		URI uri = URI.create("/korisnikusluge/id/" + savedKorisnikUsluge.getID());
		return ResponseEntity.created(uri).body(savedKorisnikUsluge);
	}
	
	@PutMapping("/korisnikusluge/id/{id}")
	public ResponseEntity<?> updateKorisnikUsluge(@RequestBody KorisnikUsluge korisnikUsluge, @PathVariable int id){
		Optional<KorisnikUsluge> updatedKorisnikUsluge = korisnikUslugeService.update(korisnikUsluge, id);
		if(updatedKorisnikUsluge.isPresent()) {
			return ResponseEntity.ok(updatedKorisnikUsluge.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " couldn't be updated because it doesn't exist");
	}
	
	@DeleteMapping("/korisnikusluge/id/{id}")
	public ResponseEntity<?> deleteKorisnikUsluge(@PathVariable int id){
		if(korisnikUslugeService.existsById(id)) {
			korisnikUslugeService.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + "has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + "couldn't be deleted because it doesn't exist");
	}
}

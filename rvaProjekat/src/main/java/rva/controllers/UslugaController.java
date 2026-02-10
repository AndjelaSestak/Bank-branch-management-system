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
import rva.models.Usluga;
import rva.services.FilijalaService;
import rva.services.KorisnikUslugeService;
import rva.services.UslugaService;
@CrossOrigin
@RestController
public class UslugaController {

	@Autowired
	private UslugaService service;
	
	@Autowired
	private FilijalaService filijalaService;
	
	@Autowired
	private KorisnikUslugeService korisnikService;
	
	@GetMapping("/usluga/filijala/{foreignKey}")
	public ResponseEntity<?> getUslugeByFilijala(@PathVariable int foreignKey){
		
		Optional <Filijala> filijala = filijalaService.findById(foreignKey);
		if(filijala.isPresent()) {
			List<Usluga> usluge = service.findByForeignKey(filijala.get());
			if(usluge.isEmpty()) {
				return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + "do not exist");
			} else {
				return ResponseEntity.ok(usluge);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
	@GetMapping("/usluga/korisnik/{foreignKey}")
	public ResponseEntity<?> getUslugeByKorisnik(@PathVariable int foreignKey){
		
		Optional <KorisnikUsluge> korisnik = korisnikService.findById(foreignKey);
		if(korisnik.isPresent()) {
			List<Usluga> usluge = service.findByForeignKey(korisnik.get());
			if(usluge.isEmpty()) {
				return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + "do not exist");
			} else {
				return ResponseEntity.ok(usluge);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
	@GetMapping("/usluga")
	public List<Usluga>getAllUslugas()
	{
		return service.getAll();
	}
	
	@GetMapping("/usluga/id/{id}")
	public ResponseEntity<?>getUslugaById(@PathVariable int id)
	{
		Optional<Usluga> usluga = service.findById(id);
		if(usluga.isPresent()) {
			return ResponseEntity.ok(usluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requeted ID: " + id + "does not exist");
	}
	
	@GetMapping("/usluga/provizija/{provizija}")
	public ResponseEntity<?> getUslugasByProvizijaLessThan(@PathVariable double provizija){
		List<Usluga> usluge = service.getUslugasByProvizijaLessThan(provizija);
		if(usluge.isEmpty()) {
				ResponseEntity.status(404).body("Resources less than provizija " + provizija + "do not exist");
		}
		return ResponseEntity.ok(usluge);
	}
	
	@PostMapping("/usluga")
	public ResponseEntity<?> createUsluga(@RequestBody Usluga usluga){
		if(service.existsById(usluga.getID())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		Usluga savedUsluga = service.create(usluga);
		URI uri = URI.create("/usluga/id/" + savedUsluga.getID());
		return ResponseEntity.created(uri).body(savedUsluga);
	}
	
	@PutMapping("/usluga/id/{id}")
	public ResponseEntity<?> updateUsluga(@RequestBody Usluga usluga, @PathVariable int id){
		Optional<Usluga> updatedUsluga = service.update(usluga, id);
		if(updatedUsluga.isPresent()) {
			return ResponseEntity.ok(updatedUsluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " couldn't be updated because it doesn't exist");
	}
	
	@DeleteMapping("/usluga/id/{id}")
	public ResponseEntity<?> deleteUsluga(@PathVariable int id){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + "has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + "couldn't be deleted because it doesn't exist");
	}
}

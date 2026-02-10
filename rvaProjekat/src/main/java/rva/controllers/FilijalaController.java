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
import rva.models.Usluga;
import rva.services.BankaService;
import rva.services.FilijalaService;

@CrossOrigin
@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService filijalaService;
	
	@Autowired
	private BankaService bankaService;
	
	@GetMapping("/filijala")
	//GetMapping je skracena verzija RequestMappinga
	//@RequestMapping(method=RequestMethod.GET, path= "/banka")
	public List<Filijala>getAllFilijalas()
	{
		return filijalaService.getAll();
	}
	
	@GetMapping("/filijala/banka/{foreignKey}")
	public ResponseEntity<?> getFilijaleByBanka(@PathVariable int foreignKey){
		
		Optional <Banka> banka = bankaService.findById(foreignKey);
		if(banka.isPresent()) {
			List<Filijala> filijale = filijalaService.findByForeignKey(banka.get());
			if(filijale.isEmpty()) {
				return ResponseEntity.status(404).body("Resource with foreign key: " + foreignKey + "do not exist");
			} else {
				return ResponseEntity.ok(filijale);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key: " + foreignKey);
	}
	
	@GetMapping("/filijala/id/{id}")
	public ResponseEntity<?>getFilijalaById(@PathVariable int id)
	{
		Optional<Filijala> filijala = filijalaService.findById(id);
		if(filijala.isPresent()) {
			return ResponseEntity.ok(filijala.get());
		}
		return ResponseEntity.status(404).body("Resource with requeted ID: " + id + "does not exist");
	}
	
	@GetMapping("/filijala/posedujeSefa/{posedujeSefa}")
	public ResponseEntity<?> getFilijalasByPosedujeSef(@PathVariable boolean posedujeSefa){
		List<Filijala> filijale = filijalaService.getFilijalasByPosedujeSef(posedujeSefa);
		if(filijale.isEmpty()) {
			ResponseEntity.status(404).body("Resources with Naziv " + posedujeSefa + "do not exist");
	}
	return ResponseEntity.ok(filijale);
	}
	
	@PostMapping("/filijala")
	public ResponseEntity<?> createFilijala(@RequestBody Filijala filijala){
		if(filijalaService.existsById(filijala.getID())) {
			return ResponseEntity.status(409).body("Resource already exists");
		}
		Filijala savedFilijala = filijalaService.create(filijala);
		URI uri = URI.create("/filijala/id/" + savedFilijala.getID());
		return ResponseEntity.created(uri).body(savedFilijala);
	}
	
	@PutMapping("/filijala/id/{id}")
	public ResponseEntity<?> updateFilijala(@RequestBody Filijala filijala, @PathVariable int id){
		Optional<Filijala> updatedFilijala = filijalaService.update(filijala, id);
		if(updatedFilijala.isPresent()) {
			return ResponseEntity.ok(updatedFilijala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " couldn't be updated because it doesn't exist");
	}
	
	@DeleteMapping("/filijala/id/{id}")
	public ResponseEntity<?> deleteFilijala(@PathVariable int id){
		if(filijalaService.existsById(id)) {
			filijalaService.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + "has been successfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + "couldn't be deleted because it doesn't exist");
	}
}

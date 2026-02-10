package rva.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.KorisnikUsluge;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KorisnikUslugeIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	void createHighestId() {
		ResponseEntity<List<KorisnikUsluge>> response = template.exchange("/korisnikusluge", HttpMethod.GET,null, new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
	
		ArrayList<KorisnikUsluge> list = (ArrayList<KorisnikUsluge>) response.getBody();
		for(int i = 0; i<list.size();i++)
		{
			if(highestId<=list.get(i).getID()) {
				highestId= list.get(i).getID()+1;
			}
		}

	}
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	int highestId;
	
	@Test
	@Order(1)
	void testGetAllKorisniks() {
		ResponseEntity<List<KorisnikUsluge>> response = template.exchange("/korisnikusluge", HttpMethod.GET,null, new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<KorisnikUsluge> korisniciUsluga = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(korisniciUsluga);
	}
	
	@Test
	@Order(2)
	void testGetKorisniksById() {
		int id = 1;
		ResponseEntity<KorisnikUsluge> response = template.exchange("/korisnikusluge/id/" + id, HttpMethod.GET,null, KorisnikUsluge.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id,response.getBody().getID());
	}
	
	@Test
	@Order(3)
	void testGetKorisniksByNaziv() {
		String ime = "Ana"; 
		ResponseEntity<List<KorisnikUsluge>> response = template.exchange("/korisnikusluge/ime/" + ime, HttpMethod.GET,null,  new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<KorisnikUsluge> korisnici = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(korisnici.get(0));
		for(KorisnikUsluge k:korisnici) {
			assertTrue(k.getIme().contains(ime));
		}

	}
	
	@Test
	@Order(4)
	void testCreateKorisnik() {
		KorisnikUsluge korisnik = new KorisnikUsluge();
		korisnik.setIme("Sanja");
		korisnik.setMaticniBroj("4758398478293");
		korisnik.setPrezime("Milosevic");
		
		HttpEntity<KorisnikUsluge> entity = new HttpEntity<KorisnikUsluge>(korisnik);
		createHighestId();
		
		ResponseEntity<KorisnikUsluge> response = template.exchange("/korisnikusluge", HttpMethod.POST,entity, KorisnikUsluge.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals("/korisnikusluge/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(korisnik.getIme(), response.getBody().getIme());
		assertEquals(korisnik.getMaticniBroj(), response.getBody().getMaticniBroj());
		assertEquals(korisnik.getPrezime(),response.getBody().getPrezime());
		
	}
	
	@Test
	@Order(5)
	void testUpdateKorisnik() {
		KorisnikUsluge korisnik = new KorisnikUsluge();
		korisnik.setIme("POST ime");
		korisnik.setMaticniBroj("POST Mbr");
		korisnik.setPrezime("POST prezime");
		
		HttpEntity<KorisnikUsluge> entity = new HttpEntity<KorisnikUsluge>(korisnik);
		//createHighestId();
		getHighestId();
		
		ResponseEntity<KorisnikUsluge> response = template.exchange("/korisnikusluge/id/"+ highestId, HttpMethod.PUT,entity, KorisnikUsluge.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(korisnik.getIme(), response.getBody().getIme());
		assertEquals(korisnik.getMaticniBroj(), response.getBody().getMaticniBroj());
		assertEquals(korisnik.getPrezime(),response.getBody().getPrezime());
	}
	
	@Test
	@Order(6)
	void testDeleteKorisnik() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/korisnikusluge/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		assertEquals(200,response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been successfully deleted"));
	}
	
}

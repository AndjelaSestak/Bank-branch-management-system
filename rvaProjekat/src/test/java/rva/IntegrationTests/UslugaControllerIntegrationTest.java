package rva.IntegrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.Usluga;

import static org.junit.jupiter.api.Assertions.*;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UslugaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	void createHighestId() {
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET,null, new ParameterizedTypeReference<List<Usluga>>() {});
	
		ArrayList<Usluga> list = (ArrayList<Usluga>) response.getBody();
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
	void testGetAllUslugas() {
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET,null, new ParameterizedTypeReference<List<Usluga>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(usluge);
	}
	
	@Test
	@Order(2)
	void testGetUslugaById() {
		int id = 1;
		ResponseEntity<Usluga> response = template.exchange("/usluga/id/" + id, HttpMethod.GET,null, Usluga.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id,response.getBody().getID());
	}
	
	@Test
	@Order(3)
	void testgetUslugasByProvizijaLessThan() {
		double provizija = 10; 
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/provizija/" + provizija, HttpMethod.GET,null,  new ParameterizedTypeReference<List<Usluga>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(usluge.get(0));
		for(Usluga u:usluge) {
			assertTrue(u.getProvizija()<provizija);
		}

	}
	
	@Test
	@Order(4)
	void testgetUslugeByKorisnik() {
		int korisnikId = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/korisnik/" + korisnikId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge =  response.getBody();

		assertEquals(200, statusCode );
		assertNotNull(usluge.get(0));
		for(Usluga u: usluge) {
			assertTrue(u.getKorisnik().getID()==korisnikId);
		}
	}
	
	@Test
	@Order(5)
	void testgetUslugeByFilijala() {
		int filijalaId = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/filijala/" + filijalaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge =  response.getBody();

		assertEquals(200, statusCode );
		assertNotNull(usluge.get(0));
		for(Usluga u: usluge) {
			assertTrue(u.getKorisnik().getID()==filijalaId);
		}
	}
	
	@Test
	@Order(6)
	void testCreateUsluga() {
		Usluga usluga = new Usluga();
		usluga.setNaziv("POST usluga");
		usluga.setOpisUsluge("POST opis");
		usluga.setProvizija(5);
		
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		createHighestId();
		
		ResponseEntity<Usluga> response = template.exchange("/usluga", HttpMethod.POST,entity, Usluga.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals("/usluga/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(usluga.getNaziv(), response.getBody().getNaziv());
		assertEquals(usluga.getOpisUsluge(), response.getBody().getOpisUsluge());
		assertEquals(usluga.getProvizija(),response.getBody().getProvizija());
		
	}
	
	@Test
	@Order(7)
	void testUpdateUsluga() {
		Usluga usluga = new Usluga();
		usluga.setNaziv("POST usluga");
		usluga.setOpisUsluge("POST opis");
		usluga.setProvizija(5);
		
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		//createHighestId();
		getHighestId();
		
		ResponseEntity<Usluga> response = template.exchange("/usluga/id/"+ highestId, HttpMethod.PUT,entity, Usluga.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(usluga.getNaziv(), response.getBody().getNaziv());
		assertEquals(usluga.getOpisUsluge(), response.getBody().getOpisUsluge());
		assertEquals(usluga.getProvizija(),response.getBody().getProvizija());
	}
	
	@Test
	@Order(8)
	void testDeleteUsluga() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/usluga/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		assertEquals(200,response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been successfully deleted"));
	}
	
}

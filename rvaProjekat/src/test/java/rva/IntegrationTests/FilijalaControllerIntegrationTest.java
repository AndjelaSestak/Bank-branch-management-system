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

import rva.models.Banka;
import rva.models.Filijala;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilijalaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	void createHighestId() {
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET,null, new ParameterizedTypeReference<List<Filijala>>() {});
	
		ArrayList<Filijala> list = (ArrayList<Filijala>) response.getBody();
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
	void testGetAllFilijalas() {
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET,null, new ParameterizedTypeReference<List<Filijala>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!filijale.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetFilijalaById() {
		int id = 1;
		ResponseEntity<Filijala> response = template.exchange("/filijala/id/" + id, HttpMethod.GET,null, Filijala.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id,response.getBody().getID());
	}
	
	@Test
	@Order(3)
	void testGetFilijaleByBanka() {
		int bankaId = 1;
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/banka/"+ bankaId, HttpMethod.GET,null,  new ParameterizedTypeReference<List<Filijala>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale = response.getBody();
		assertEquals(200, statusCode);
		//assertNotNull(filijale.get(0));
		assertTrue(!filijale.isEmpty());

		for(Filijala f: filijale) {
			//assertTrue(f.getBanka().getID() == bankaId);
			assertEquals(bankaId, f.getBanka().getID());
		}

	}
	
	@Test
	@Order(4)
	void testGetFilijalaByPosedujeSef() {
		boolean posedujeSefa = true; 
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/posedujeSefa/" + posedujeSefa, HttpMethod.GET,null,  new ParameterizedTypeReference<List<Filijala>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(filijale.get(0));
		for(Filijala f:filijale) {
			assertTrue(f.isPosedujeSef());
		}
	}
	
	@Test
	@Order(5)
	void testCreateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setAdresa("POST adresa");
		filijala.setBrojPultova(3);
		//filijala.setPosedujeSef(false);
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		createHighestId();
		
		ResponseEntity<Filijala> response = template.exchange("/filijala", HttpMethod.POST,entity, Filijala.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals("/filijala/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(filijala.getAdresa(), response.getBody().getAdresa());
		assertEquals(filijala.getBrojPultova(), response.getBody().getBrojPultova());		
	}
	
	@Test
	@Order(6)
	void testUpdateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setAdresa("PUT adresa");
		filijala.setBrojPultova(3);
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		//createHighestId();
		getHighestId();
		
		ResponseEntity<Filijala> response = template.exchange("/filijala/id/"+ highestId, HttpMethod.PUT,entity, Filijala.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(filijala.getAdresa(), response.getBody().getAdresa());
		assertEquals(filijala.getBrojPultova(), response.getBody().getBrojPultova());
		
		
	}
	
	@Test
	@Order(7)
	void testDeleteFilijala() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/filijala/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		assertEquals(200,response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been successfully deleted"));
	}
	

	

}

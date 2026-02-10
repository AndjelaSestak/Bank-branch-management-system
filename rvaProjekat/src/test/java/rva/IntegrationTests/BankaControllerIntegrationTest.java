package rva.IntegrationTests;

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

import rva.models.Banka;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	void createHighestId() {
		ResponseEntity<List<Banka>> response = template.exchange("/banka", HttpMethod.GET,null, new ParameterizedTypeReference<List<Banka>>() {});
	
		ArrayList<Banka> list = (ArrayList<Banka>) response.getBody();
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
	void testGetAllBankas() {
		ResponseEntity<List<Banka>> response = template.exchange("/banka", HttpMethod.GET,null, new ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banke = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!banke.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetBankaById() {
		int id = 1;
		ResponseEntity<Banka> response = template.exchange("/banka/id/" + id, HttpMethod.GET,null, Banka.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertNotNull(response.getBody());
		assertEquals(id,response.getBody().getID());
	}
	
	@Test
	@Order(3)
	void testGetBankasByNaziv() {
		String naziv = "Postanska stedionica"; 
		ResponseEntity<List<Banka>> response = template.exchange("/banka/naziv/" + naziv, HttpMethod.GET,null,  new ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banke = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(banke.get(0));
		for(Banka b:banke) {
			assertTrue(b.getNaziv().contains(naziv));
		}

	}
	
	@Test
	@Order(4)
	void testCreateBanka() {
		Banka banka = new Banka();
		banka.setNaziv("POST naziv");
		banka.setKontakt("POST kontakt");
		//banka.setPIB(7989);
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		createHighestId();
		
		ResponseEntity<Banka> response = template.exchange("/banka", HttpMethod.POST,entity, Banka.class);
		
		assertEquals(201, response.getStatusCode().value());
		assertEquals("/banka/id/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(banka.getNaziv(), response.getBody().getNaziv());
		assertEquals(banka.getKontakt(), response.getBody().getKontakt());
		
	}
	
	@Test
	@Order(5)
	void testUpdateBanka() {
		Banka banka = new Banka();
		banka.setNaziv("PUT naziv");
		banka.setKontakt("PUT kontakt");
		//banka.setPIB(7989);
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		//createHighestId();
		getHighestId();
		
		ResponseEntity<Banka> response = template.exchange("/banka/id/"+ highestId, HttpMethod.PUT,entity, Banka.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(banka.getNaziv(), response.getBody().getNaziv());
		assertEquals(banka.getKontakt(), response.getBody().getKontakt());
		
		
	}
	
	@Test
	@Order(6)
	void testDeleteBanka() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/banka/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		assertEquals(200,response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been successfully deleted"));
	}
	
	/*@Test
	@Order(3)
	void testGetBankasByNaziv() {
		int foreignKey  = 1;
		ResponseEntity<List<Banka>> response = template.exchange("/banka/naziv/"!!!ovo se menja + foreignKey, HttpMethod.GET,null,  new ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banke = response.getBody();
		assertTrue(b.getBanka().getId() == foreignKey)
		assertEquals(200, statusCode);
		assertNotNull(banke.get(0));
		for(Banka b:banke) {
			assertTrue(b.getNaziv().contains(naziv));
			assertTrue(b.getBanka().getId() == foreignKey)
		}

	}
	*/

}

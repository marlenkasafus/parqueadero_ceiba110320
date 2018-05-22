package parqueadero_ddd.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Tcrm;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConsumeTCRM {

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	private int localServerPort;

	@Test
	public void consumeTCRMTest() {
		ResponseEntity<Tcrm> responseEntityTicket = restTemplate
				.getForEntity("http://localhost:" + localServerPort + "/TCRMService", Tcrm.class);
		assertEquals(HttpStatus.OK, responseEntityTicket.getStatusCode());
	}

}

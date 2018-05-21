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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CeladorServiceTicketNoEncontradoTest {
	
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	private int localServerPort;
	
	@Test
	public void realizarSolicitudSalida() throws InterruptedException {
		ResponseEntity<String> responseEntityTicket = restTemplate.getForEntity("http://localhost:"+localServerPort+"/celador/retiro/solicitud?id=250", String.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntityTicket.getStatusCode());
		assertEquals("Ticket no encontrado, verifique el número e intente nuevamente", responseEntityTicket.getBody());
	}

}

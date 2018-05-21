package parqueadero_ddd.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CeladorServiceTest {
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@LocalServerPort
	private int localServerPort; 
	
	@Test
	public void registrarIngreso() {
		Vehiculo vehiculo = new Vehiculo("YDX10D",TipoVehiculoEnum.MOTO,220);
		ResponseEntity<Ticket> responseEntityTicket = restTemplate.postForEntity("http://localhost:"+localServerPort+"/celador/ingreso", vehiculo,Ticket.class);
		assertEquals(HttpStatus.OK, responseEntityTicket.getStatusCode());
	}
	
	@Test
	public void consultarVehiculos() {
		ResponseEntity<Object[]> responseTest = restTemplate.getForEntity("http://localhost:"+localServerPort+"/celador/vehiculos/actuales",Object[].class);
		assertEquals(HttpStatus.OK, responseTest.getStatusCode());
		assertEquals(1,responseTest.getBody().length);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void realizarSolicitudSalida() throws InterruptedException {
		ResponseEntity<Object[]> responseTest = restTemplate.getForEntity("http://localhost:"+localServerPort+"/celador/vehiculos/actuales",Object[].class);
		HashMap<String, Object> hashMap = (HashMap<String, Object>) responseTest.getBody()[0];
		ResponseEntity<Ticket> responseEntityTicket = restTemplate.getForEntity("http://localhost:"+localServerPort+"/celador/retiro/solicitud?id="+hashMap.get("id"), Ticket.class);
		Ticket ticket = responseEntityTicket.getBody();
		assertEquals(HttpStatus.OK, responseEntityTicket.getStatusCode());
		assertEquals(new BigDecimal(500), ticket.getValorPagar());
		ResponseEntity<Ticket> responseEntityTicketRetiro = restTemplate.postForEntity("http://localhost:"+localServerPort+"/celador/retiro/solicitud", ticket,Ticket.class);
		assertEquals(HttpStatus.OK, responseEntityTicketRetiro.getStatusCode());
		assertEquals(EstadoParqueaderoEnum.LIBERADO,responseEntityTicketRetiro.getBody().getEstadoParqueaderoEnum());
	}

}

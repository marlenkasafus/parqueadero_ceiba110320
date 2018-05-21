package parqueadero_ddd.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CeladorServiceNoCuposTest {

private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@LocalServerPort
	private int localServerPort;
	
	@Before
	public void setup() {
		for (int i = 0; i < 10; i++) {
			Vehiculo vehiculo = new Vehiculo("YDX"+i+"0D",TipoVehiculoEnum.MOTO,220);
			restTemplate.postForEntity("http://localhost:"+localServerPort+"/celador/ingreso", vehiculo,String.class);
		}
	}
	
	@Test
	public void realizarIngresoNoHayCupos() {
		Vehiculo vehiculo = new Vehiculo("YDX10D",TipoVehiculoEnum.MOTO,220);
		ResponseEntity<String> responseEntityTicket = restTemplate.postForEntity("http://localhost:"+localServerPort+"/celador/ingreso", vehiculo,String.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntityTicket.getStatusCode());
		assertEquals("No hay celdas disponibles.",responseEntityTicket.getBody());
	}

}

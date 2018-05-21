package parqueadero_ddd.domain.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Calendario;
import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource("classpath:parqueaderotest.properties")
@EnableAutoConfiguration(exclude=FlywayAutoConfiguration.class)
public class CalendarioTest {
	
	@Autowired
	private Calendario calendario;
	
	@Autowired
	private Celador celador;
	
//	@Autowired
//	private RestriccionPlacaRepositorioJPA restriccionPlacaRepositorioJPA;
////	
//	@Before//TODO:: Error
//	public void setup() {
//		List<Integer> diasLunesSemana = new ArrayList<>();
//		diasLunesSemana.add(1);
//		RestriccionPlacaEntidad restriccionPlacaEntidadDiaLunes = new RestriccionPlacaEntidad('A',diasLunesSemana);
//		restriccionPlacaRepositorioJPA.save(restriccionPlacaEntidadDiaLunes);
//		
//		List<Integer> diasDomingoSemana = new ArrayList<>();
//		diasDomingoSemana.add(7);
//		RestriccionPlacaEntidad restriccionPlacaEntidadDiaDomingo = new RestriccionPlacaEntidad('A',diasDomingoSemana);
//		restriccionPlacaRepositorioJPA.save(restriccionPlacaEntidadDiaDomingo);
//		
//		
//	}

	@Test
	public void noEsDiaHabilCarroPlacaADiaMartes() {
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 8, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO,0));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil",  e.getMessage());
		}
	}
	
	@Test
	public void noEsDiaHabilCarroPlacaADiaSabado() {
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 5, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO,0));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil",  e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoCarroDiaNoHabil() throws CeladorException  {
		try {
			Vehiculo vehiculo = new Vehiculo("AZD123", TipoVehiculoEnum.CARRO,0);
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 5, 9, 32));
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil",  e.getMessage());
		}	
	}

}

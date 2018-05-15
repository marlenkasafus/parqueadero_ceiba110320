package parqueadero_ddd.domain.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import parqueadero_ddd.persistencia.entidad.RestriccionPlacaEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.RestriccionPlacaRepositorioJPA;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource("classpath:parqueaderotest.properties")
public class CalendarioTest {
	
	@Autowired
	private Calendario calendario;
	
	@Autowired
	private Celador celador;
	
	@Autowired
	private RestriccionPlacaRepositorioJPA restriccionPlacaRepositorioJPA;
	
	@Before
	public void setup() {
		List<Integer> diasLunesSemana = new ArrayList<>();
		diasLunesSemana.add(1);
		RestriccionPlacaEntidad restriccionPlacaEntidadDiaLunes = new RestriccionPlacaEntidad('A',diasLunesSemana);
		restriccionPlacaRepositorioJPA.save(restriccionPlacaEntidadDiaLunes);
		
		List<Integer> diasDomingoSemana = new ArrayList<>();
		diasDomingoSemana.add(7);
		RestriccionPlacaEntidad restriccionPlacaEntidadDiaDomingo = new RestriccionPlacaEntidad('A',diasDomingoSemana);
		restriccionPlacaRepositorioJPA.save(restriccionPlacaEntidadDiaDomingo);
		
		
	}

	@Test
	public void noEsDiaHabilCarroPlacaADiaLunes() {
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 7, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no est� en un dia h�bil",  e.getMessage());
		}
	}
	
	@Test
	public void noEsDiaHabilCarroPlacaADiaDomingo() {
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 6, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no est� en un dia h�bil",  e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoCarroDiaNoHabil() throws CeladorException  {
		try {
			Vehiculo vehiculo = new Vehiculo("AZD123", TipoVehiculoEnum.CARRO);
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 6, 9, 32));
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no est� en un dia h�bil",  e.getMessage());
		}	
	}

}
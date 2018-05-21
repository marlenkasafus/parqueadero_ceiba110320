package parqueadero_ddd.domain.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Calendario;
import parqueadero_ddd.domain.RestriccionPlaca;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.persistencia.repositorio.RestriccionPlacaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CalendarioTest {
	
	@InjectMocks
	private Calendario calendario;
	
	@Mock
	private RestriccionPlacaRepositorio restriccionPlacaRepositorio;
	
	@Before
	public void setup() {
		 MockitoAnnotations.initMocks(this);
	}

	@Test
	public void noEsDiaHabilCarroPlacaADiaMiercoles() {
		List<RestriccionPlaca> restriccionPlacas = new ArrayList<>();
		List<Integer> diasSemana = new ArrayList<>();
		diasSemana.add(1);
		restriccionPlacas.add(new RestriccionPlaca('A', diasSemana));
		Mockito.when(restriccionPlacaRepositorio.findAll()).thenReturn(restriccionPlacas);
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 23, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO,0));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil",  e.getMessage());
		}
	}
	
	@Test
	public void noEsDiaHabilCarroPlacaADiaMartes() {
		List<RestriccionPlaca> restriccionPlacas = new ArrayList<>();
		List<Integer> diasSemana = new ArrayList<>();
		diasSemana.add(7);
		restriccionPlacas.add(new RestriccionPlaca('A', diasSemana));
		Mockito.when(restriccionPlacaRepositorio.findAll()).thenReturn(restriccionPlacas);
		try {
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 22, 9, 32));
			calendario.esDiaHabilParaVehiculo(new Vehiculo("AZD123",TipoVehiculoEnum.CARRO,0));
			fail();
		} catch (CalendarioException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil",  e.getMessage());
		}
	}

}

package parqueadero_ddd.domain.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
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
import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.RestriccionPlaca;
import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.configuration.ParqueaderoConfiguracion;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CeladorException;
import parqueadero_ddd.exception.ParqueaderoException;
import parqueadero_ddd.persistencia.repositorio.RestriccionPlacaRepositorio;
import parqueadero_ddd.persistencia.repositorio.TicketRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CeladorTest {
	
	@InjectMocks
	private Celador celador;
	
	@Mock
	private Calendario calendario;
	
	@Mock
	private ParqueaderoConfiguracion parqueadero;
	
	@Mock
	private TicketRepositorio parqueaderoRepositorio;
	
	@Mock
	private RestriccionPlacaRepositorio restriccionPlacaRepositorio;
	
	@Before
	public void setup() {
	        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void noHayEspaciosDisponiblesCarro() {
		try {
			Mockito.when(parqueadero.getCantidadCupos(TipoVehiculoEnum.CARRO)).thenReturn(10);
			Mockito.when(parqueadero.getCantidadCeldasEnUso(TipoVehiculoEnum.CARRO)).thenReturn(10);
			celador.hayEspaciosDisponibles(TipoVehiculoEnum.CARRO);	
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoMotoDiaLunesSinDisponibilidad() {
		List<RestriccionPlaca> restriccionPlacas = new ArrayList<>();
		List<Integer> diasSemana = new ArrayList<>();
		diasSemana.add(1);
		restriccionPlacas.add(new RestriccionPlaca('A', diasSemana));
		Mockito.when(restriccionPlacaRepositorio.findAll()).thenReturn(restriccionPlacas);
		try {
			Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.MOTO,0);
			Mockito.when(parqueadero.getCantidadCupos(TipoVehiculoEnum.MOTO)).thenReturn(10);
			Mockito.when(parqueadero.getCantidadCeldasEnUso(TipoVehiculoEnum.MOTO)).thenReturn(10);
			calendario.setFechaActual(LocalDateTime.of(2018, 05, 7, 9, 32));
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoCarroDiaMiercolesSinDisponibilidad() {
		List<RestriccionPlaca> restriccionPlacas = new ArrayList<>();
		List<Integer> diasSemana = new ArrayList<>();
		diasSemana.add(1);
		restriccionPlacas.add(new RestriccionPlaca('A', diasSemana));
		Mockito.when(restriccionPlacaRepositorio.findAll()).thenReturn(restriccionPlacas);
		try {
			Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.CARRO,0);
			Mockito.when(parqueadero.getCantidadCupos(TipoVehiculoEnum.CARRO)).thenReturn(10);
			Mockito.when(parqueadero.getCantidadCeldasEnUso(TipoVehiculoEnum.CARRO)).thenReturn(10);
			calendario.setFechaActual(LocalDateTime.of(2018, 5, 16, 9, 32));
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}
	}
	
	@Test
	public void solicitarRetiroVehiculoNoExistente() throws ParqueaderoException {
		Ticket ticket = new Ticket();
		Mockito.when(parqueaderoRepositorio.findById(1)).thenReturn(null);
		try {
		celador.solicitudRetiroVehiculo(ticket,null);
		fail();
		} catch (CeladorException e) {
			assertEquals("Ticket no encontrado, verifique el número e intente nuevamente", e.getMessage());
		}
	}
	
	@Test
	public void solicitarRetiroVehiculo() throws CeladorException, ParqueaderoException {
		Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.CARRO,0);
		Ticket ticket = new Ticket(1, vehiculo, LocalDateTime.of(2018, 5, 16, 7, 15),null,null,null);
		Mockito.when(parqueaderoRepositorio.findById(1)).thenReturn(ticket);
		Ticket ticketretiro = celador.solicitudRetiroVehiculo(ticket,LocalDateTime.of(2018, 5, 16, 9, 15));
		assertEquals(new BigDecimal("2000"), ticketretiro.getValorPagar());
	}
}

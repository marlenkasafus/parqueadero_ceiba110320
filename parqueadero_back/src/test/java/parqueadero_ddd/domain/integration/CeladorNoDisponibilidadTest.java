package parqueadero_ddd.domain.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;
import parqueadero_ddd.persistencia.entidad.TicketEntidad;
import parqueadero_ddd.persistencia.entidad.VehiculoEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.TicketRepositorioJPA;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource("classpath:parqueaderotest.properties")
public class CeladorNoDisponibilidadTest {
	
	@Autowired
	private Celador celador;
	
	@Autowired
	private TicketRepositorioJPA parqueaderoRepositorio;
	
	@Before
	public void setup() {
		for (int i = 0; i < 10; i++) {
			TicketEntidad parqueaderoEntidad = new TicketEntidad(null, new VehiculoEntidad(TipoVehiculoEnum.CARRO.getCodigo(), "ASD123",0),EstadoParqueaderoEnum.OCUPADO.getCodigo(), LocalDateTime.now(),null,null);
			parqueaderoRepositorio.saveAndFlush(parqueaderoEntidad);			
		}
		
		for (int i = 0; i < 5; i++) {
			TicketEntidad parqueaderoEntidad = new TicketEntidad(null, new VehiculoEntidad(TipoVehiculoEnum.MOTO.getCodigo(), "ASD123",0),EstadoParqueaderoEnum.OCUPADO.getCodigo(), LocalDateTime.now(),null,null);
			parqueaderoRepositorio.saveAndFlush(parqueaderoEntidad);			
		}
	}

	@Test
	public void noHayEspaciosDisponiblesCarro() {
		try {
			celador.hayEspaciosDisponibles(TipoVehiculoEnum.CARRO);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoCarroNoDisponibilidad() throws CalendarioException {
		try {
			Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.CARRO,0);
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}	
	}
	
	@Test
	public void noHayEspaciosDisponiblesMoto() {
		try {
			celador.hayEspaciosDisponibles(TipoVehiculoEnum.MOTO);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}
	}
	
	@Test
	public void realizarIngresoMotoNoDisponibilidad() throws CalendarioException {
		try {
			Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.MOTO,0);
			celador.solicitudIngresoVehiculo(vehiculo);
			fail();
		} catch (CeladorException e) {
			assertEquals("No hay celdas disponibles.", e.getMessage());
		}	
	}

}

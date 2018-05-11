package com.ceiba.parqueadero.controller.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.DayOfWeek;
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

import com.ceiba.parqueadero.controlador.ParqueaderoController;
import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.dominio.RestriccionPlaca;
import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.configuracion.ParqueaderoConfiguration;
import com.ceiba.parqueadero.dominio.enums.EstadoParqueaderoEnum;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.exception.ControllerException;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;
import com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;
import com.ceiba.parqueadero.persistencia.repositorio.RestriccionPlacaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ParqueaderoControllerTest {
	
	@InjectMocks
	private ParqueaderoController parqueaderoController;

	@Mock
	private ParqueaderoRepository parqueaderoRepository;
	
	@Mock
	private ParqueaderoConfiguration parqueaderoConfiguration;
	
	@Mock
	private RestriccionPlacaRepository restriccionPlacaRepository;

	@Before
	public void setup() {
	        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validarDisponibidadCarro() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.CARRO);
		Mockito.when(parqueaderoConfiguration.getCupoMaximoCarros()).thenReturn(2);
		assertTrue(parqueaderoController.hayDisponibilidad(vehiculo));
	}
	
	@Test
	public void validarDisponibidadMoto() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		Mockito.when(parqueaderoConfiguration.getCupoTotalMotos()).thenReturn(2);
		assertTrue(parqueaderoController.hayDisponibilidad(vehiculo));
	}
	
	@Test
	public void validarNoDisponibidadCarro() {
		Vehiculo vehiculo = new Vehiculo();
		List<ParqueaderoEntity> parqueaderoEntities = new ArrayList<>();
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.CARRO);
		Mockito.when(parqueaderoRepository.getCantidadParqueaderosUtilizados(vehiculo.getTipoVehiculoEnum())).thenReturn(2);
		Mockito.when(parqueaderoConfiguration.getCupoMaximoCarros()).thenReturn(2);
		
		assertFalse(parqueaderoController.hayDisponibilidad(vehiculo));
	}
	
	@Test
	public void validarNoDisponibidadMoto() {
		Vehiculo vehiculo = new Vehiculo();
		List<ParqueaderoEntity> parqueaderoEntities = new ArrayList<>();
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		parqueaderoEntities.add(new ParqueaderoEntity(1, LocalDateTime.now(),EstadoParqueaderoEnum.OCUPADO.getCodigo(),new VehiculoEntity(), LocalDateTime.now()));
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		Mockito.when(parqueaderoRepository.getCantidadParqueaderosUtilizados(vehiculo.getTipoVehiculoEnum())).thenReturn(2);
		Mockito.when(parqueaderoConfiguration.getCupoTotalMotos()).thenReturn(2);
		
		assertFalse(parqueaderoController.hayDisponibilidad(vehiculo));
	}
	
	@Test
	public void obtenerCupoDisponibleMoto() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		Mockito.when(parqueaderoConfiguration.getCupoTotalMotos()).thenReturn(10);
		assertEquals(10, parqueaderoController.getCupoDisponible(vehiculo));
	}
	
	@Test
	public void obtenerCupoDisponibleCarro() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.CARRO);
		Mockito.when(parqueaderoConfiguration.getCupoMaximoCarros()).thenReturn(20);
		assertEquals(20, parqueaderoController.getCupoDisponible(vehiculo));
	}
	
	@Test
	public void ingresoPlacaALunesDiaHabil() throws ControllerException {
		Parqueadero parqueadero = new Parqueadero();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		vehiculo.setPlaca("AYT12A");
		parqueadero.setVehiculo(vehiculo);
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 7, 10, 20));
		assertTrue(parqueaderoController.esDiaHabilParaPlaca(parqueadero));
	}
	
	@Test
	public void ingresoPlacaANoEsDiaHabil() {
		Parqueadero parqueadero = new Parqueadero();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		vehiculo.setPlaca("AYT12A");
		parqueadero.setVehiculo(vehiculo);
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 7, 10, 20));
		try {
			List<RestriccionPlaca> restriccionPlacas = new ArrayList<>();
			restriccionPlacas.add(new RestriccionPlaca('A', new ArrayList<Integer>() {
				private static final long serialVersionUID = 1L;

			{
			    add(DayOfWeek.MONDAY.getValue());
			}}));
			Mockito.when(restriccionPlacaRepository.findAll()).thenReturn(restriccionPlacas);
			parqueaderoController.esDiaHabilParaPlaca(parqueadero);
			fail();
		} catch (ControllerException e) {
			assertEquals("No puede ingresar porque no está en un dia hábil", e.getMessage());
		}
	}
	
	@Test
	public void ingresoPlacaADomingoDiaHabil() throws ControllerException {
		Parqueadero parqueadero = new Parqueadero();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		vehiculo.setPlaca("AYT12A");
		parqueadero.setVehiculo(vehiculo);
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 6, 10, 20));
		Mockito.when(restriccionPlacaRepository.findAll()).thenReturn(new ArrayList<>());
		assertTrue(parqueaderoController.esDiaHabilParaPlaca(parqueadero));
	}
	
	@Test
	public void ingresoPlacaXMartesEsDiaHabil() throws ControllerException {
		Parqueadero parqueadero = new Parqueadero();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.MOTO);
		vehiculo.setPlaca("XYT12A");
		parqueadero.setVehiculo(vehiculo);
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 9, 10, 20));
		assertTrue(parqueaderoController.esDiaHabilParaPlaca(parqueadero));
	}

}

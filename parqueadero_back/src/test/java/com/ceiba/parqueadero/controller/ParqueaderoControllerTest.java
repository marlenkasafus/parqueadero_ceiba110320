package com.ceiba.parqueadero.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.configuracion.ParqueaderoConfiguration;
import com.ceiba.parqueadero.dominio.enums.EstadoParqueaderoEnum;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;
import com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;

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
		Mockito.when(parqueaderoRepository.findByEstadoAndTipoVehiculo("O",vehiculo.getTipoVehiculoEnum().getCodigo())).thenReturn(parqueaderoEntities);
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
		Mockito.when(parqueaderoRepository.findByEstadoAndTipoVehiculo("O",vehiculo.getTipoVehiculoEnum().getCodigo())).thenReturn(parqueaderoEntities);
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

}

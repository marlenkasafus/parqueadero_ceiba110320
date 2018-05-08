package com.ceiba.parqueadero.domain;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class ParqueaderoTest {

	@Test
	public void validarPlacaIniciaEnADiaHabilTest() {
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 8, 11, 20));
		Vehiculo moto = new Vehiculo("ADX10D");
		Assert.assertTrue(parqueadero.validarDiaHabilPorPlaca(moto));
	}
	
	@Test
	public void ValidarPlacaIniciaEnADiaDomingoNoHabil() {
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setFechaIngreso(LocalDateTime.of(2018, 5, 6, 11, 20));
		Vehiculo moto = new Vehiculo("ADX10D");
		Assert.assertFalse(parqueadero.validarDiaHabilPorPlaca(moto));
	}

}

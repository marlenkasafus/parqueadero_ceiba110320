package com.ceiba.parqueadero.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Parqueadero {
	private LocalDateTime fechaIngreso;

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public boolean validarDiaHabilPorPlaca(Vehiculo moto) {
		if (DayOfWeek.SUNDAY.equals(fechaIngreso.getDayOfWeek())) {
			return 'a' == moto.getPlaca().charAt(0);
		}
		
		return true;
	}

}

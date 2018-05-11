package com.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

public class Parqueadero {
	
	private Vehiculo vehiculo;
	
	private LocalDateTime fechaIngreso;

	public Parqueadero() {
	}
	
	public Parqueadero(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	
	
}

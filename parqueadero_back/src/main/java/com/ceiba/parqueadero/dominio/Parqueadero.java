package com.ceiba.parqueadero.dominio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Parqueadero {

	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
	private LocalDateTime fechaIngreso;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
	private LocalDateTime fechaSalida;
	
	public Parqueadero() {
		
	}

	public Parqueadero(Integer id, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}
	
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

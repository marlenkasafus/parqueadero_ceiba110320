package com.ceiba.parqueadero.dominio;

import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;

public class Vehiculo {
	
	private TipoVehiculoEnum tipoVehiculoEnum;
	
	private String placa;
	
	public Vehiculo(TipoVehiculoEnum tipoVehiculoEnum,String placa) {
		this.tipoVehiculoEnum = tipoVehiculoEnum;
		this.placa = placa;
	}
	
	public Vehiculo() {
		
	}

	public TipoVehiculoEnum getTipoVehiculoEnum() {
		return tipoVehiculoEnum;
	}

	public void setTipoVehiculoEnum(TipoVehiculoEnum tipoVehiculoEnum) {
		this.tipoVehiculoEnum = tipoVehiculoEnum;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
}

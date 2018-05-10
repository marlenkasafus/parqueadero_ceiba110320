package com.ceiba.parqueadero.dominio;

import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;

public class Vehiculo {
	
	private TipoVehiculoEnum tipoVehiculoEnum;

	public TipoVehiculoEnum getTipoVehiculoEnum() {
		return tipoVehiculoEnum;
	}

	public void setTipoVehiculoEnum(TipoVehiculoEnum tipoVehiculoEnum) {
		this.tipoVehiculoEnum = tipoVehiculoEnum;
	}
	
}

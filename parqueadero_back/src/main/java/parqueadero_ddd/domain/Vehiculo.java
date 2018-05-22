package parqueadero_ddd.domain;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

public class Vehiculo {

	private String placa;
	
	private TipoVehiculoEnum tipoVehiculoEnum;
	
	private int cilindraje;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String placa,TipoVehiculoEnum tipoVehiculoEnum, int cilindraje) {
		this.placa = placa;
		this.tipoVehiculoEnum = tipoVehiculoEnum;
		this.cilindraje = cilindraje;
	}

	public char obtenerCaracterInicialPlaca() {
		return placa.charAt(0);
	}

	public TipoVehiculoEnum getTipoVehiculoEnum() {
		return tipoVehiculoEnum;
	}

	public String getPlaca() {
		return placa;
	}	

	public int getCilindraje() {
		return cilindraje;
	}
}

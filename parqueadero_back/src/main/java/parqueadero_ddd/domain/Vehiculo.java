package parqueadero_ddd.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

public class Vehiculo {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

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

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String toJson() throws JsonProcessingException {
		return objectMapper.writeValueAsString(this);
	}
	
	
	
	

}

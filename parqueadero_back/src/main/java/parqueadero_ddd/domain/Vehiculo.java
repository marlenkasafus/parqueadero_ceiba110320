package parqueadero_ddd.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

public class Vehiculo {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private String placa;
	
	private TipoVehiculoEnum tipoVehiculoEnum;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String placa,TipoVehiculoEnum tipoVehiculoEnum) {
		this.placa = placa;
		this.tipoVehiculoEnum = tipoVehiculoEnum;
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

	public String toJson() throws JsonProcessingException {
		return objectMapper.writeValueAsString(this);
	}
	
	
	
	

}

package com.ceiba.parqueadero.dominio.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:parqueadero.properties")
public class ParqueaderoConfiguration {
	
	@Value("${parqueadero.max.carros}")
	private int cupoMaximoCarros;
	
	@Value("${parqueadero.max.motos}")
	private int cupoTotalMotos;
	
	public int getCupoMaximoCarros() {
		return cupoMaximoCarros;
	}
	public int getCupoTotalMotos() {
		return cupoTotalMotos;
	}
}

package com.ceiba.parqueadero.persistencia.builder;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;

public class ParqueaderoBuilder {
	
	private ParqueaderoBuilder() {}
	
	public static Parqueadero convertirEntidadADominio(ParqueaderoEntity parqueaderoEntity) {
		return new Parqueadero(parqueaderoEntity.getId(),parqueaderoEntity.getFechaIngreso(),parqueaderoEntity.getFechaSalida());		
	}
	
	public static ParqueaderoEntity convertirDominioAEntidad(Parqueadero parqueadero) {
		return new ParqueaderoEntity(parqueadero.getId(),parqueadero.getFechaIngreso(),parqueadero.getFechaSalida());
	}

}

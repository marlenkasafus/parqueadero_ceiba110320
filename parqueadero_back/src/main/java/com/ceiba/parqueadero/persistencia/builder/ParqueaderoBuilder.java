package com.ceiba.parqueadero.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;

public class ParqueaderoBuilder {
	
	private ParqueaderoBuilder() {
		
	}
	
	public static List<Parqueadero> convertirADominio(List<ParqueaderoEntity> parqueaderoEntities){
		List<Parqueadero> parqueaderos = new ArrayList<>();
		for (ParqueaderoEntity parqueaderoEntity : parqueaderoEntities) {
			Vehiculo vehiculo = new Vehiculo(TipoVehiculoEnum.getByCodigo(parqueaderoEntity.getVehiculoEntity().getTipoVehiculo()),parqueaderoEntity.getVehiculoEntity().getPlaca());
			parqueaderos.add(new Parqueadero(vehiculo,parqueaderoEntity.getFechaIngreso()));
		}
		return parqueaderos;
	}

}

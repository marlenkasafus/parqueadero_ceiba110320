package com.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.dominio.enums.EstadoParqueaderoEnum;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.persistencia.repositorio.jpa.ParqueaderoRepositoryJPA;

@Repository
public class ParqueaderoRepository {
	
	@Autowired
	private ParqueaderoRepositoryJPA parqueaderoRepository;
	
	public List<Parqueadero> findByEstadoAndTipoVehiculo(EstadoParqueaderoEnum estadoParqueaderoEnum, TipoVehiculoEnum tipoVehiculoEnum){
		parqueaderoRepository.findByEstadoAndTipoVehiculo(estadoParqueaderoEnum.getCodigo(), tipoVehiculoEnum.getCodigo());
		return null;
	}

	public int getCantidadParqueaderosUtilizados(TipoVehiculoEnum tipoVehiculoEnum) {
		return findByEstadoAndTipoVehiculo(EstadoParqueaderoEnum.OCUPADO,tipoVehiculoEnum).size();
	}


}

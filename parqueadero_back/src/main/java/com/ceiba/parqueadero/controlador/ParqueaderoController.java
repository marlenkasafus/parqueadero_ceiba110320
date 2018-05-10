package com.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.configuracion.ParqueaderoConfiguration;
import com.ceiba.parqueadero.dominio.enums.EstadoParqueaderoEnum;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;

@Controller
public class ParqueaderoController {
	
	@Autowired
	private ParqueaderoConfiguration parqueaderoConfiguration;
	
	@Autowired
	private ParqueaderoRepository parqueaderoRepository;

	public boolean hayDisponibilidad(Vehiculo vehiculo) {
		return getCupoDisponible(vehiculo)>parqueaderoRepository.findByEstadoAndTipoVehiculo(EstadoParqueaderoEnum.OCUPADO.getCodigo(),vehiculo.getTipoVehiculoEnum().getCodigo()).size();
	}

	public int getCupoDisponible(Vehiculo vehiculo) {
		int cupoDisponible = 0;
		if (TipoVehiculoEnum.CARRO.equals(vehiculo.getTipoVehiculoEnum())) {
			cupoDisponible = parqueaderoConfiguration.getCupoMaximoCarros();
		} else if (TipoVehiculoEnum.MOTO.equals(vehiculo.getTipoVehiculoEnum())) {
			cupoDisponible = parqueaderoConfiguration.getCupoTotalMotos();
		}
		return cupoDisponible;
	}

}

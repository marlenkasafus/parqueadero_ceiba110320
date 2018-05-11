package com.ceiba.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.dominio.RestriccionPlaca;
import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.configuracion.ParqueaderoConfiguration;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.exception.ControllerException;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;
import com.ceiba.parqueadero.persistencia.repositorio.RestriccionPlacaRepository;

@Controller
public class ParqueaderoController {
	
	@Autowired
	private ParqueaderoConfiguration parqueaderoConfiguration;
	
	@Autowired
	private ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	private RestriccionPlacaRepository restriccionPlacaRepository;

	public boolean hayDisponibilidad(Vehiculo vehiculo) {
		return getCupoDisponible(vehiculo)>parqueaderoRepository.getCantidadParqueaderosUtilizados(vehiculo.getTipoVehiculoEnum());
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

	public boolean esDiaHabilParaPlaca(Parqueadero parqueadero) throws ControllerException {
		List<RestriccionPlaca> restriccionPlacas = restriccionPlacaRepository.findAll();
		char caracterPlacaVehiculo = parqueadero.getVehiculo().getPlaca().toUpperCase().charAt(0);
		int diaSemana = parqueadero.getFechaIngreso().getDayOfWeek().getValue();
		for (RestriccionPlaca restriccionPlaca : restriccionPlacas) {
			if (restriccionPlaca.getCaracterPlaca()==caracterPlacaVehiculo && restriccionPlaca.getDiasDeLaSemana().contains(diaSemana)) {
				throw new ControllerException("No puede ingresar porque no está en un dia hábil");
			}
		}
		return true;
	}

}

package com.ceiba.parqueadero.servicio;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.controlador.ParqueaderoController;
import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.dominio.Vehiculo;
import com.ceiba.parqueadero.dominio.enums.TipoVehiculoEnum;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;
import com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
import com.ceiba.parqueadero.persistencia.repositorio.jpa.ParqueaderoRepositoryJPA;
import com.ceiba.parqueadero.persistencia.repositorio.jpa.VehiculoRepositoryJPA;

@RestController
@RequestMapping(path="/parqueadero")
public class ParqueaderoService {
	
	@Autowired
	private ParqueaderoController parqueaderoController;
	
	@Autowired
	private ParqueaderoRepositoryJPA parqueaderoRepository;
	
	@Autowired
	private VehiculoRepositoryJPA vehiculoRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String registrarIngreso(@RequestBody Parqueadero parqueadero) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setTipoVehiculo("C");
		vehiculoRepository.save(vehiculoEntity);
		ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity(null, LocalDateTime.now(), "O", vehiculoEntity, LocalDateTime.now());
		parqueaderoRepository.save(parqueaderoEntity);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculoEnum(TipoVehiculoEnum.CARRO);
		parqueaderoController.hayDisponibilidad(vehiculo);
		return "";
	}

}

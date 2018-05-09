package com.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.persistencia.builder.ParqueaderoBuilder;
import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;

@Controller
public class ParqueaderoController {
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;

	public ParqueaderoEntity registrarIngreso(Parqueadero parqueadero) {
		return parqueaderoRepository.save(ParqueaderoBuilder.convertirDominioAEntidad(parqueadero));
	}

}

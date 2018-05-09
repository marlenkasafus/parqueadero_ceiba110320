package com.ceiba.parqueadero.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.controlador.ParqueaderoController;
import com.ceiba.parqueadero.dominio.Parqueadero;

@RestController
@RequestMapping(path="/parqueadero")
public class ParqueaderoService {
	
	@Autowired
	private ParqueaderoController parqueaderoController;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String registrarIngreso(@RequestBody Parqueadero parqueadero) {
		parqueaderoController.registrarIngreso(parqueadero);
		return "";
	}

}

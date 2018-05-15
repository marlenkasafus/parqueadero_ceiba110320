package parqueadero_ddd.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parqueadero_ddd.domain.Calendario;
import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.ParqueaderoPOJO;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;

@RestController
@RequestMapping("/celador")
public class CeladorService {
	
	@Autowired
	private Celador celador;
	
	@Autowired
	private Calendario calendario;
	
	@RequestMapping(value="/ingreso",method=RequestMethod.POST)
	public ResponseEntity<?> solicitudIngresoVehiculo(@RequestBody Vehiculo vehiculo){
		calendario.setFechaActual(LocalDateTime.now());
		ParqueaderoPOJO parqueaderoPOJO;
		try {
			parqueaderoPOJO = celador.solicitudIngresoVehiculo(vehiculo);
		} catch (CeladorException | CalendarioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(parqueaderoPOJO, HttpStatus.OK);
	}

}

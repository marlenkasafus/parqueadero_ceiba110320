package parqueadero_ddd.service;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@RequestMapping(value="/retiro/solicitud",method=RequestMethod.GET)
	public ResponseEntity<?> solicitudRetiroVehiculo(ParqueaderoPOJO parqueaderoPOJORequest){
		ParqueaderoPOJO parqueaderoPOJO;
		try {
			parqueaderoPOJO = celador.solicitudRetiroVehiculo(parqueaderoPOJORequest, LocalDateTime.now());
		} catch (CeladorException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(parqueaderoPOJO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/retiro/solicitud",method=RequestMethod.PUT)
	public ResponseEntity<ParqueaderoPOJO> registrarRetiroVehiculo(@RequestBody ParqueaderoPOJO parqueaderoPOJORequest){
		ParqueaderoPOJO parqueaderoPOJO;
			parqueaderoPOJO = celador.registrarRetiro(parqueaderoPOJORequest);
		return new ResponseEntity<>(parqueaderoPOJO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehiculos/actuales",method=RequestMethod.GET)
	public ResponseEntity<List<ParqueaderoPOJO>> vehiculosExistentes(){
		List<ParqueaderoPOJO> parqueaderoPOJO = celador.consultarParqueaderosEnUso();
		return new ResponseEntity<>(parqueaderoPOJO, HttpStatus.OK);
	}
	
	

}

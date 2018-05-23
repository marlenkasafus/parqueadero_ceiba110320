package parqueadero_ddd.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parqueadero_ddd.domain.Calendario;
import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.exception.CeladorException;

@RestController
@RequestMapping("/celador")
@CrossOrigin
public class CeladorController {
	
	@Autowired
	private Celador celador;
	
	@Autowired
	private Calendario calendario;
	
	@RequestMapping(value="/ingreso",method=RequestMethod.POST)
	public ResponseEntity<Object> solicitudIngresoVehiculo(@RequestBody Vehiculo vehiculo){
		calendario.setFechaActual(LocalDateTime.now());
		Ticket ticket;
		try {
			ticket = celador.solicitudIngresoVehiculo(vehiculo);
		} catch (CeladorException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@RequestMapping(value="/retiro/solicitud",method=RequestMethod.GET)
	public ResponseEntity<Object> solicitudRetiroVehiculo(Ticket ticketRequest){
		Ticket ticket;
		try {
			ticket = celador.solicitudRetiroVehiculo(ticketRequest, LocalDateTime.now());
		} catch (CeladorException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@RequestMapping(value="/retiro/solicitud",method=RequestMethod.POST)
	public ResponseEntity<Ticket> registrarRetiroVehiculo(@RequestBody Ticket ticketRequest){
		Ticket ticket = celador.registrarRetiro(ticketRequest);
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehiculos/actuales",method=RequestMethod.GET)
	public ResponseEntity<List<Ticket>> vehiculosExistentes(){
		List<Ticket> ticket = celador.consultarParqueaderosEnUso();
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	

}

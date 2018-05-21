package parqueadero_ddd.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero_ddd.domain.configuration.ParqueaderoConfiguracion;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;
import parqueadero_ddd.exception.ParqueaderoException;
import parqueadero_ddd.persistencia.repositorio.TicketRepositorio;

@Service
public class Celador {
	
	private Calendario calendario;
	private ParqueaderoConfiguracion parqueadero;
	private TicketRepositorio ticketRepositorio;
	
	@Autowired
	public Celador(Calendario calendario, ParqueaderoConfiguracion parqueadero, TicketRepositorio ticketRepositorio) {
		this.calendario = calendario;
		this.parqueadero = parqueadero;
		this.ticketRepositorio = ticketRepositorio;
	}
	
	public void hayEspaciosDisponibles(TipoVehiculoEnum tipoVehiculo) throws CeladorException {
		if (parqueadero.getCantidadCupos(tipoVehiculo)<=parqueadero.getCantidadCeldasEnUso(tipoVehiculo)) {
			throw new CeladorException("No hay celdas disponibles.");
		}
	}

	public Ticket solicitudIngresoVehiculo(Vehiculo vehiculo) throws CeladorException {
		hayEspaciosDisponibles(vehiculo.getTipoVehiculoEnum());
		try {
			calendario.esDiaHabilParaVehiculo(vehiculo);			
		} catch (CalendarioException e) {
			throw new CeladorException(e.getMessage());
		}
		return generarIngresoVehiculo(vehiculo);
	}

	private Ticket generarIngresoVehiculo(Vehiculo vehiculo) {
		Ticket ticket = new Ticket(vehiculo, calendario.getFechaActual());
		return ticketRepositorio.save(ticket);
	}

	public Ticket solicitudRetiroVehiculo(Ticket ticket,LocalDateTime fechaSalida) throws CeladorException {
		ticket = ticketRepositorio.findById(ticket.getId());
		if (ticket == null) {
			throw new CeladorException("Ticket no encontrado, verifique el número e intente nuevamente");
		}
		ticket.setFechaSalida(fechaSalida);
		System.out.println(ticket.getFechaIngreso());
		System.out.println(ticket.getFechaSalida());
		try {
			calcularValorParqueo(ticket);					
		} catch (ParqueaderoException e) {
			throw new CeladorException(e.getMessage());
		}
		return ticket;
	}
	
	public Ticket registrarRetiro(Ticket ticket) {
		ticket.setEstadoParqueaderoEnum(EstadoParqueaderoEnum.LIBERADO);
		return ticketRepositorio.save(ticket);
	}
	

	public void calcularValorParqueo(Ticket ticket) throws ParqueaderoException {
		ticket.setValorPagar(Calculadora.getInstance(ticket).generarCobro(ticket));
	}
	
	public List<Ticket> consultarParqueaderosEnUso(){
		return ticketRepositorio.getParqueaderosEnUso();
	}
	

}

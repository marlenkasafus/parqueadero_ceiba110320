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
import parqueadero_ddd.persistencia.repositorio.ParqueaderoRepositorio;

@Service
public class Celador {
	
	private Calendario calendario;
	private ParqueaderoConfiguracion parqueadero;
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	public Celador(Calendario calendario, ParqueaderoConfiguracion parqueadero, ParqueaderoRepositorio parqueaderoRepositorio) {
		this.calendario = calendario;
		this.parqueadero = parqueadero;
		this.parqueaderoRepositorio = parqueaderoRepositorio;
	}
	
	public void hayEspaciosDisponibles(TipoVehiculoEnum tipoVehiculo) throws CeladorException {
		if (parqueadero.getCantidadCupos(tipoVehiculo)<=parqueadero.getCantidadCeldasEnUso(tipoVehiculo)) {
			throw new CeladorException("No hay celdas disponibles.");
		}
	}

	public Ticket solicitudIngresoVehiculo(Vehiculo vehiculo) throws CeladorException, CalendarioException {
		hayEspaciosDisponibles(vehiculo.getTipoVehiculoEnum());
		calendario.esDiaHabilParaVehiculo(vehiculo);
		return generarIngresoVehiculo(vehiculo);
	}

	private Ticket generarIngresoVehiculo(Vehiculo vehiculo) {
		Ticket ticket = new Ticket(vehiculo, calendario.getFechaActual());
		return parqueaderoRepositorio.save(ticket);
	}

	public Ticket solicitudRetiroVehiculo(Ticket ticket,LocalDateTime fechaSalida) throws CeladorException {
		ticket = parqueaderoRepositorio.findById(ticket.getId());
		if (ticket == null) {
			throw new CeladorException("Ticket no encontrado, verifique el número e intente nuevamente");
		}
		ticket.setFechaSalida(fechaSalida);
		calcularValorParqueo(ticket);		
		return ticket;
	}
	
	public Ticket registrarRetiro(Ticket ticket) {
		ticket.setEstadoParqueaderoEnum(EstadoParqueaderoEnum.LIBERADO);
		return parqueaderoRepositorio.save(ticket);
	}
	

	public void calcularValorParqueo(Ticket ticket) {
		ticket.setValorPagar(ticket.getTicket().generarCobro(ticket));
	}
	
	public List<Ticket> consultarParqueaderosEnUso(){
		return parqueaderoRepositorio.getParqueaderosEnUso();
	}
	

}

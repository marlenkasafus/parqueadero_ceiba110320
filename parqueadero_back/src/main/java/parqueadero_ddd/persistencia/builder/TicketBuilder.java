package parqueadero_ddd.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.entidad.TicketEntidad;

public class TicketBuilder {

	private TicketBuilder() {

	}

	public static TicketEntidad convertirAParqueaderoEntidad(Ticket ticket) {
		return new TicketEntidad(ticket.getId(),
				VehiculoBuilder.convertirAVehiculoEntidad(ticket.getVehiculo()),
				ticket.getEstadoParqueaderoEnum().getCodigo(), ticket.getFechaIngreso(),
				ticket.getFechaSalida(), ticket.getValorPagar());
	}

	public static Ticket convertirAticket(TicketEntidad ticketEntidad) {
		Vehiculo vehiculo = new Vehiculo(ticketEntidad.getVehiculo().getPlaca(),
				TipoVehiculoEnum.getByCodigo(ticketEntidad.getVehiculo().getTipo()),
				ticketEntidad.getVehiculo().getCilindraje());
		return new Ticket(ticketEntidad.getId(), vehiculo, ticketEntidad.getFechaIngreso(),
				ticketEntidad.getFechaSalida(), ticketEntidad.getValorPagar(),
				EstadoParqueaderoEnum.getByCodigo(ticketEntidad.getEstado()));
	}

	public static List<Ticket> convertirAParqueaderosPOJO(List<TicketEntidad> findAllEstadoOcupado) {
		List<Ticket> pojos = new ArrayList<>();
		for (TicketEntidad parqueaderoEntidad : findAllEstadoOcupado) {
			pojos.add(convertirAticket(parqueaderoEntidad));
		}
		return pojos;
	}

}

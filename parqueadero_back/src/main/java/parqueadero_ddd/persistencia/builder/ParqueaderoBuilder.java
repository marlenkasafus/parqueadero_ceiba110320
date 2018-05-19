package parqueadero_ddd.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;

public class ParqueaderoBuilder {

	private ParqueaderoBuilder() {

	}

	public static ParqueaderoEntidad convertirAParqueaderoEntidad(Ticket ticket) {
		return new ParqueaderoEntidad(ticket.getId(),
				VehiculoBuilder.convertirAVehiculoEntidad(ticket.getVehiculo()),
				ticket.getEstadoParqueaderoEnum().getCodigo(), ticket.getFechaIngreso(),
				ticket.getFechaSalida(), ticket.getValorPagar());
	}

	public static Ticket convertirAticket(ParqueaderoEntidad parqueaderoEntidad) {
		Vehiculo vehiculo = new Vehiculo(parqueaderoEntidad.getVehiculo().getPlaca(),
				TipoVehiculoEnum.getByCodigo(parqueaderoEntidad.getVehiculo().getTipo()),
				parqueaderoEntidad.getVehiculo().getCilindraje());
		return new Ticket(parqueaderoEntidad.getId(), vehiculo, parqueaderoEntidad.getFechaIngreso(),
				parqueaderoEntidad.getFechaSalida(), parqueaderoEntidad.getValorPagar(),
				EstadoParqueaderoEnum.getByCodigo(parqueaderoEntidad.getEstado()));
	}

	public static List<Ticket> convertirAParqueaderosPOJO(List<ParqueaderoEntidad> findAllEstadoOcupado) {
		List<Ticket> pojos = new ArrayList<>();
		for (ParqueaderoEntidad parqueaderoEntidad : findAllEstadoOcupado) {
			pojos.add(convertirAticket(parqueaderoEntidad));
		}
		return pojos;
	}

}

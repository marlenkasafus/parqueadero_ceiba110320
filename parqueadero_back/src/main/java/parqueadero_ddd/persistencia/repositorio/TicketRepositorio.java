package parqueadero_ddd.persistencia.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.builder.TicketBuilder;
import parqueadero_ddd.persistencia.entidad.TicketEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.TicketRepositorioJPA;

@Repository
public class TicketRepositorio {
	
	@Autowired
	private TicketRepositorioJPA ticketRepositorioJPA;

	public int getCantidadParqueaderosEnUsoPorVehiculo(TipoVehiculoEnum vehiculo) {
		return ticketRepositorioJPA.countByEstadoAndTipoVehiculo(EstadoParqueaderoEnum.OCUPADO.getCodigo(),vehiculo.getCodigo());
	}

	public Ticket save(Ticket ticket) {
		return TicketBuilder.convertirAticket(ticketRepositorioJPA.save(TicketBuilder.convertirAParqueaderoEntidad(ticket)));
	}
	
	public Ticket findById(Integer id) {
		Optional<TicketEntidad> optionalParqueaderoEntidad = ticketRepositorioJPA.findById(id);
		return optionalParqueaderoEntidad.isPresent()?TicketBuilder.convertirAticket(optionalParqueaderoEntidad.get()):null;
	}

	public List<Ticket> getParqueaderosEnUso() {
		return TicketBuilder.convertirAParqueaderosPOJO(ticketRepositorioJPA.findAllEstadoOcupado(EstadoParqueaderoEnum.OCUPADO.getCodigo()));
	}
}

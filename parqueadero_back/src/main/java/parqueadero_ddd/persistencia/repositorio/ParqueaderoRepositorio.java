package parqueadero_ddd.persistencia.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.builder.ParqueaderoBuilder;
import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.ParqueaderoRepositorioJPA;

@Repository
public class ParqueaderoRepositorio {
	
	@Autowired
	private ParqueaderoRepositorioJPA parqueaderoRepositorioJPA;

	public int getCantidadParqueaderosEnUsoPorVehiculo(TipoVehiculoEnum vehiculo) {
		return parqueaderoRepositorioJPA.countByEstadoAndTipoVehiculo(EstadoParqueaderoEnum.OCUPADO.getCodigo(),vehiculo.getCodigo());
	}

	public Ticket save(Ticket ticket) {
		return ParqueaderoBuilder.convertirAticket(parqueaderoRepositorioJPA.save(ParqueaderoBuilder.convertirAParqueaderoEntidad(ticket)));
	}
	
	public Ticket findById(Integer id) {
		Optional<ParqueaderoEntidad> optionalParqueaderoEntidad = parqueaderoRepositorioJPA.findById(id);
		return optionalParqueaderoEntidad.isPresent()?ParqueaderoBuilder.convertirAticket(optionalParqueaderoEntidad.get()):null;
	}

	public List<Ticket> getParqueaderosEnUso() {
		return ParqueaderoBuilder.convertirAParqueaderosPOJO(parqueaderoRepositorioJPA.findAllEstadoOcupado(EstadoParqueaderoEnum.OCUPADO.getCodigo()));
	}
}

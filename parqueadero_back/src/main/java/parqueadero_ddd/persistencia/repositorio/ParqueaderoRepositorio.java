package parqueadero_ddd.persistencia.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.domain.ParqueaderoPOJO;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.builder.ParqueaderoBuilder;
import parqueadero_ddd.persistencia.repositorio.jpa.ParqueaderoRepositorioJPA;

@Repository
public class ParqueaderoRepositorio {
	
	@Autowired
	private ParqueaderoRepositorioJPA parqueaderoRepositorioJPA;

	public int getCantidadParqueaderosEnUsoPorVehiculo(TipoVehiculoEnum vehiculo) {
		return parqueaderoRepositorioJPA.countByEstadoAndTipoVehiculo(EstadoParqueaderoEnum.OCUPADO.getCodigo(),vehiculo.getCodigo());
	}

	public ParqueaderoPOJO realizarIngreso(ParqueaderoPOJO parqueaderoPOJO) {
		return ParqueaderoBuilder.convertirAParqueaderoPOJO(parqueaderoRepositorioJPA.save(ParqueaderoBuilder.convertirAParqueaderoEntidad(parqueaderoPOJO)));
	}
}

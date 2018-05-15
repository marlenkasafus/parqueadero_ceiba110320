package parqueadero_ddd.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.domain.RestriccionPlaca;
import parqueadero_ddd.persistencia.entidad.RestriccionPlacaEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.RestriccionPlacaRepositorioJPA;

@Repository
public class RestriccionPlacaRepositorio {

	@Autowired
	private RestriccionPlacaRepositorioJPA restriccionPlacaRepositoryJPA;
	
	public List<RestriccionPlaca> findAll(){
		List<RestriccionPlacaEntidad> restriccionPlacaEntities = restriccionPlacaRepositoryJPA.findAll();
		List<RestriccionPlaca> restriccionesPlaca = new ArrayList<>();
		for (RestriccionPlacaEntidad restriccionPlacaEntitie : restriccionPlacaEntities) {
			restriccionesPlaca.add(new RestriccionPlaca(restriccionPlacaEntitie.getCaracterPlaca(),restriccionPlacaEntitie.getDiasDeLaSemana()));
		}
		return restriccionesPlaca;
	}
}

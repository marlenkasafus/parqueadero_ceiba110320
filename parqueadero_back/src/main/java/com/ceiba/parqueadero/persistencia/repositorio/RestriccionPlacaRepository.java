package com.ceiba.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero.dominio.RestriccionPlaca;
import com.ceiba.parqueadero.persistencia.entidad.RestriccionPlacaEntity;
import com.ceiba.parqueadero.persistencia.repositorio.jpa.RestriccionPlacaRepositoryJPA;

@Repository
public class RestriccionPlacaRepository {

	@Autowired
	private RestriccionPlacaRepositoryJPA restriccionPlacaRepositoryJPA;
	
	public List<RestriccionPlaca> findAll(){
		List<RestriccionPlacaEntity> restriccionPlacaEntities = restriccionPlacaRepositoryJPA.findAll();
		List<RestriccionPlaca> restriccionesPlaca = new ArrayList<>();
		for (RestriccionPlacaEntity restriccionPlacaEntitie : restriccionPlacaEntities) {
			restriccionesPlaca.add(new RestriccionPlaca(restriccionPlacaEntitie.getCaracterPlaca(),restriccionPlacaEntitie.getDiasDeLaSemana()));
		}
		return restriccionesPlaca;
	}
}

package parqueadero_ddd.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.persistencia.entidad.RestriccionPlacaEntidad;

@Repository
public interface RestriccionPlacaRepositorioJPA extends JpaRepository<RestriccionPlacaEntidad,Integer>{

}

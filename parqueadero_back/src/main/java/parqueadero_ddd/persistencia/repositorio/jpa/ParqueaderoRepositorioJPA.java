package parqueadero_ddd.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;

public interface ParqueaderoRepositorioJPA extends JpaRepository<ParqueaderoEntidad,Integer>{
	
	@Query("select count(P) from ParqueaderoEntidad P where P.estado = :estado")
	int countByEstado(@Param("estado") String estado);

	@Query("select count(P) from ParqueaderoEntidad P where P.estado = :estado and P.vehiculo.tipo = :tipoVehiculo")
	int countByEstadoAndTipoVehiculo(@Param("estado") String estado, @Param("tipoVehiculo") String tipoVehiculo);

}

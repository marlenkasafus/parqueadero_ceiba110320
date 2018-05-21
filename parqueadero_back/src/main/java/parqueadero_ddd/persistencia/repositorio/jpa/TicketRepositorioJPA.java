package parqueadero_ddd.persistencia.repositorio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import parqueadero_ddd.persistencia.entidad.TicketEntidad;

public interface TicketRepositorioJPA extends JpaRepository<TicketEntidad,Integer>{
	
	@Query("select count(P) from TicketEntidad P where P.estado = :estado")
	int countByEstado(@Param("estado") String estado);

	@Query("select count(P) from TicketEntidad P where P.estado = :estado and P.vehiculo.tipo = :tipoVehiculo")
	int countByEstadoAndTipoVehiculo(@Param("estado") String estado, @Param("tipoVehiculo") String tipoVehiculo);

	@Query("select P from TicketEntidad P where P.estado = :estado")
	List<TicketEntidad> findAllEstadoOcupado(@Param("estado") String estado);

}

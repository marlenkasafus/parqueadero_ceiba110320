package com.ceiba.parqueadero.persistencia.repositorio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;

@Repository
public interface ParqueaderoRepositoryJPA extends JpaRepository<ParqueaderoEntity,Integer> {
	
	@Query("SELECT PE FROM ParqueaderoEntity PE WHERE PE.estado = :estado AND PE.vehiculoEntity.tipoVehiculo = :tipoVehiculo")
	List<ParqueaderoEntity> findByEstadoAndTipoVehiculo(@Param("estado") String estado,@Param("tipoVehiculo") String tipoVehiculo);

}

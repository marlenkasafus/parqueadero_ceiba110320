package parqueadero_ddd.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import parqueadero_ddd.persistencia.entidad.VehiculoEntidad;

@Repository
public interface VehiculoRepositorioJPA extends JpaRepository<VehiculoEntidad,String>{

}

package com.ceiba.parqueadero.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
@Repository
public interface VehiculoRepositoryJPA extends JpaRepository<VehiculoEntity,Integer>{

}

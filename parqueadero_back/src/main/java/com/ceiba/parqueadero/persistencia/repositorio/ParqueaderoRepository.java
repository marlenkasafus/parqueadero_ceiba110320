package com.ceiba.parqueadero.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero.persistencia.entidad.ParqueaderoEntity;

@Repository
public interface ParqueaderoRepository extends JpaRepository<ParqueaderoEntity,Integer> {

}

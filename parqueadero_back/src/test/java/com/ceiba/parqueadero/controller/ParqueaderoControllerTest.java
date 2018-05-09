package com.ceiba.parqueadero.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero.dominio.Parqueadero;
import com.ceiba.parqueadero.persistencia.builder.ParqueaderoBuilder;
import com.ceiba.parqueadero.persistencia.repositorio.ParqueaderoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParqueaderoControllerTest {
	
	@Autowired
	private ParqueaderoRepository parqueaderoRepository;
	
	@Test
	public void insertarParqueaderoTest() {
		
			parqueaderoRepository.save(ParqueaderoBuilder.convertirDominioAEntidad(new Parqueadero(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(2))));
			
			assertEquals(1, parqueaderoRepository.findAll().size());

		
	}

}

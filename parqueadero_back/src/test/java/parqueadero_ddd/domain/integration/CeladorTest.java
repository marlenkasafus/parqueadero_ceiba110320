package parqueadero_ddd.domain.integration;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import parqueadero_ddd.domain.Celador;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;
import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;
import parqueadero_ddd.persistencia.repositorio.jpa.ParqueaderoRepositorioJPA;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@TestPropertySource("classpath:parqueaderotest.properties")
public class CeladorTest {
	
	@Autowired
	private Celador celador;
	
	@Autowired
	private ParqueaderoRepositorioJPA parqueaderoRepositorioJPA;
	
	@Test
	public void realizarIngresoExitoso() throws CeladorException, CalendarioException {
		Vehiculo vehiculo = new Vehiculo("YDX10D", TipoVehiculoEnum.MOTO);
		celador.solicitudIngresoVehiculo(vehiculo);
		
		List<ParqueaderoEntidad> parqueaderoEntidads = parqueaderoRepositorioJPA.findAll();
		
		assertTrue(parqueaderoEntidads.size()>0);
		
	}

}
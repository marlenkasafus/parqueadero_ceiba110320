package parqueadero_ddd.domain.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import parqueadero_ddd.Application;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.service.CeladorService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:parqueaderotest.properties")
@ContextConfiguration(classes=Application.class)
@WebMvcTest(CeladorService.class)
public class CeladorServiceTest {
	
	 @Autowired
	 private MockMvc mvc;

	 @Ignore
	@Test
	public void servicioRestRealizarIngresoExitoso() throws Exception {
		Vehiculo vehiculo = new Vehiculo("ASD123", TipoVehiculoEnum.CARRO,0);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/celador/ingreso")
				.accept(MediaType.APPLICATION_JSON)
				.content(vehiculo.toJson());
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}

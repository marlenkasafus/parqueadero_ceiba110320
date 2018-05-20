package parqueadero_ddd.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
@JsonIgnoreProperties
public class CalculadoraCarro extends Calculadora{

	private static final BigDecimal VALOR_DIA = new BigDecimal("8000");
	private static final BigDecimal VALOR_HORA = new BigDecimal("1000");

	@Override
	public BigDecimal generarCobro(Ticket ticket) {
		return super.getValorParqueo(ticket,VALOR_DIA,VALOR_HORA);
	}

}

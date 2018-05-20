package parqueadero_ddd.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
@JsonIgnoreProperties
public class CalculadoraMoto extends Calculadora {

	private static final BigDecimal VALOR_ADICIONAL = new BigDecimal(2000);
	private static final BigDecimal VALOR_DIA = new BigDecimal(600);
	private static final BigDecimal VALOR_HORA = new BigDecimal(500);

	@Override
	public BigDecimal generarCobro(Ticket ticket) {
		BigDecimal valorParqueo = super.getValorParqueo(ticket, VALOR_DIA, VALOR_HORA);
		if (500 < ticket.getVehiculo().getCilindraje()) {
			valorParqueo = valorParqueo.add(VALOR_ADICIONAL);
		}
		return valorParqueo;
	}

}

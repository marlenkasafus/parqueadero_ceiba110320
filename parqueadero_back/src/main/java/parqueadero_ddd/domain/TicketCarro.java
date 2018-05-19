package parqueadero_ddd.domain;

import java.math.BigDecimal;

public class TicketCarro extends TicketCobro{

	private static final BigDecimal VALOR_DIA = new BigDecimal("8000");
	private static final BigDecimal VALOR_HORA = new BigDecimal("1000");

	@Override
	public BigDecimal generarCobro(Ticket ticket) {
		return super.getValorParqueo(ticket,VALOR_DIA,VALOR_HORA);
	}

}

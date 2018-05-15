package parqueadero_ddd.domain;

import java.math.BigDecimal;

public class TicketCarro extends Ticket{

	@Override
	public BigDecimal generarCobro(ParqueaderoPOJO parqueaderoPOJO) {
		long cantidadHoras = super.calcularTiempoParqueo(parqueaderoPOJO);
		long cantidadDias = super.calcularCantidadDias(cantidadHoras);
		long cantidadHorasRestantes = super.calcularHorasRestantes(cantidadHoras,cantidadDias);
		long cantidadDiasPorRegla = super.calcularCantidadDiasPorRegla(cantidadHorasRestantes);
		BigDecimal valorDia = getValorDia().multiply(new BigDecimal(cantidadDias));
		if (0 < cantidadDiasPorRegla) {
			valorDia = valorDia.add(getValorDia().multiply(new BigDecimal(cantidadDiasPorRegla)));
		} else {
			valorDia = valorDia.add(getValorHora(cantidadHorasRestantes));
		}
		return valorDia;
	}

	private BigDecimal getValorHora(long cantidadHoras) {
		return new BigDecimal(cantidadHoras*1000);
	}

	private BigDecimal getValorDia() {
		return new BigDecimal(8000);
	}

}

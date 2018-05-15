package parqueadero_ddd.domain;

import java.math.BigDecimal;

public class TicketMoto extends Ticket {

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
		if (500 < parqueaderoPOJO.getVehiculo().getCilindraje()) {
			valorDia = valorDia.add(new BigDecimal("2000"));
		}
		return valorDia;
	}

	private BigDecimal getValorHora(long cantidadHoras) {
		return new BigDecimal(cantidadHoras*500);
	}

	private BigDecimal getValorDia() {
		return new BigDecimal(600);
	}

}

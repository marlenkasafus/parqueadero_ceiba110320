package parqueadero_ddd.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.ParqueaderoException;

public abstract class Calculadora {
	
	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
	
	public long calcularTiempoParqueo(Ticket ticket) {
		return getTime(ticket.getFechaIngreso(), ticket.getFechaSalida());
	}
	
	public long getTime(LocalDateTime dob, LocalDateTime now) {
        Duration duration = Duration.between(dob, now);
        long seconds = duration.getSeconds();
        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);
        if (0 < minutes || 0 < secs) {
        	hours = hours+1;
		}
        return hours;
    }

	public abstract BigDecimal generarCobro(Ticket ticket);

	public long calcularCantidadDias(long cantidadHoras) {
		return cantidadHoras / 24;
	}

	public long calcularHorasRestantes(long cantidadHoras, long cantidadDias) {
		return cantidadHoras-(cantidadDias*24);
	}

	public long calcularCantidadDiasPorRegla(long cantidadHorasRestantes) {
		if (9<=cantidadHorasRestantes ) {
			return 1;			
		}
		return 0;
	}
	
	public BigDecimal getValorParqueo(Ticket ticket, BigDecimal valorDia, BigDecimal valorHora) {
		long cantidadHoras = calcularTiempoParqueo(ticket);
		long cantidadDias = calcularCantidadDias(cantidadHoras);
		long cantidadHorasRestantes = calcularHorasRestantes(cantidadHoras,cantidadDias);
		long cantidadDiasPorRegla = calcularCantidadDiasPorRegla(cantidadHorasRestantes);
		BigDecimal valorParquo = valorDia.multiply(new BigDecimal(cantidadDias));
		if (0 < cantidadDiasPorRegla) {
			valorParquo = valorParquo.add(valorDia.multiply(new BigDecimal(cantidadDiasPorRegla)));
		} else {
			valorParquo = valorParquo.add(getValorHora(cantidadHorasRestantes, valorHora));
		}
		return valorParquo;
	}

	private BigDecimal getValorHora(long cantidadHoras, BigDecimal valorHora) {
		return valorHora.multiply(new BigDecimal(cantidadHoras));
	}

	public static Calculadora getInstance(Ticket ticket) throws ParqueaderoException {
		if (TipoVehiculoEnum.CARRO.equals(ticket.getVehiculo().getTipoVehiculoEnum())) {
			return new CalculadoraCarro();
		} else if (TipoVehiculoEnum.MOTO.equals(ticket.getVehiculo().getTipoVehiculoEnum())) {
			return new CalculadoraMoto();
		} else {
			throw new ParqueaderoException("El tipo de vehiculo es obligatorio para calcular el valor");
		}
	}
}

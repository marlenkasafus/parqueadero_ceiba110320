package parqueadero_ddd.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Ticket {
	
	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	public long calcularTiempoParqueo(ParqueaderoPOJO parqueaderoPOJO) {
		return getTime(parqueaderoPOJO.getFechaIngreso(), parqueaderoPOJO.getFechaSalida());
	}
	
	private long getTime(LocalDateTime dob, LocalDateTime now) {
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

	public abstract BigDecimal generarCobro(ParqueaderoPOJO parqueaderoPOJO);

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

}

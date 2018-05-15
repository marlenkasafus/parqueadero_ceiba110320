package parqueadero_ddd.domain;

import java.time.LocalDateTime;

import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

public class ParqueaderoPOJO {
	
	private Vehiculo vehiculo;
	private LocalDateTime fechaIngreso;
	private EstadoParqueaderoEnum estadoParqueaderoEnum;
	private LocalDateTime fechaSalida;
	private Ticket ticket;
	
	public ParqueaderoPOJO(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.estadoParqueaderoEnum = EstadoParqueaderoEnum.OCUPADO;
		if (TipoVehiculoEnum.CARRO.equals(this.vehiculo.getTipoVehiculoEnum())) {
			ticket = new TicketCarro();
		}else if (TipoVehiculoEnum.MOTO.equals(this.vehiculo.getTipoVehiculoEnum())) {
			ticket = new TicketMoto();
		}
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public EstadoParqueaderoEnum getEstadoParqueaderoEnum() {
		return estadoParqueaderoEnum;
	}
	

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public Ticket getTicket() {
		return ticket;
	}
	
	

}

package parqueadero_ddd.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParqueaderoPOJO {

	private Integer id;
	private Vehiculo vehiculo;
	private LocalDateTime fechaIngreso;
	private EstadoParqueaderoEnum estadoParqueaderoEnum;
	private LocalDateTime fechaSalida;

	@JsonIgnore
	private Ticket ticket;
	private BigDecimal valorPagar;

	public ParqueaderoPOJO(Integer id, Vehiculo vehiculo, LocalDateTime fechaIngreso) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.estadoParqueaderoEnum = EstadoParqueaderoEnum.OCUPADO;
		if (TipoVehiculoEnum.CARRO.equals(this.vehiculo.getTipoVehiculoEnum())) {
			ticket = new TicketCarro();
		} else if (TipoVehiculoEnum.MOTO.equals(this.vehiculo.getTipoVehiculoEnum())) {
			ticket = new TicketMoto();
		}
	}

	public Integer getId() {
		return id;
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

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	
}

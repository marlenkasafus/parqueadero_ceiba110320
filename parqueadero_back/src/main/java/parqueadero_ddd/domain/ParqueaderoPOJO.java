package parqueadero_ddd.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParqueaderoPOJO {

	private Integer id;
	private Vehiculo vehiculo;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)  
	@JsonSerialize(using = LocalDateTimeSerializer.class)  
	private LocalDateTime fechaIngreso;
	private EstadoParqueaderoEnum estadoParqueaderoEnum;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)  
	@JsonSerialize(using = LocalDateTimeSerializer.class)  
	private LocalDateTime fechaSalida;
	@JsonIgnore
	private Ticket ticket;
	private BigDecimal valorPagar;
	
	public ParqueaderoPOJO() {
	}

	public ParqueaderoPOJO(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.estadoParqueaderoEnum = EstadoParqueaderoEnum.OCUPADO;
		if (this.vehiculo != null) {
			if (TipoVehiculoEnum.CARRO.equals(this.vehiculo.getTipoVehiculoEnum())) {
				ticket = new TicketCarro();
			} else if (TipoVehiculoEnum.MOTO.equals(this.vehiculo.getTipoVehiculoEnum())) {
				ticket = new TicketMoto();
			}			
		}
	}
	
	public ParqueaderoPOJO(Integer id, Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,BigDecimal valorPagar, EstadoParqueaderoEnum estadoParqueaderoEnum) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.estadoParqueaderoEnum = estadoParqueaderoEnum;
		this.fechaSalida = fechaSalida;
		this.valorPagar = valorPagar;
		if (this.vehiculo != null) {
			if (TipoVehiculoEnum.CARRO.equals(this.vehiculo.getTipoVehiculoEnum())) {
				ticket = new TicketCarro();
			} else if (TipoVehiculoEnum.MOTO.equals(this.vehiculo.getTipoVehiculoEnum())) {
				ticket = new TicketMoto();
			}			
		}
	}

	public Integer getId() {
		return id;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public EstadoParqueaderoEnum getEstadoParqueaderoEnum() {
		return estadoParqueaderoEnum;
	}
	
	public void setEstadoParqueaderoEnum(EstadoParqueaderoEnum estadoParqueaderoEnum) {
		this.estadoParqueaderoEnum = estadoParqueaderoEnum;
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

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}
	
	

	
}

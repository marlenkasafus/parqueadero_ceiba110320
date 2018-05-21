package parqueadero_ddd.persistencia.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class TicketEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idVehiculo",referencedColumnName="id") 
	private VehiculoEntidad vehiculo;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="fechaIngreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name="fechaSalida")
	private LocalDateTime fechaSalida;
	
	@Column(name="valorPagar")
	private BigDecimal valorPagar;
	
	public TicketEntidad() {
	}
	
	public TicketEntidad(Integer id, VehiculoEntidad vehiculo, String estado, LocalDateTime fechaIngreso,LocalDateTime fechaSalida, BigDecimal valorPagar) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorPagar = valorPagar;
	}

	
	public Integer getId() {
		return id;
	}

	public VehiculoEntidad getVehiculo() {
		return vehiculo;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}
	
	
}

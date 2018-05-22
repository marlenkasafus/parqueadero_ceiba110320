package parqueadero_ddd.persistencia.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class TicketEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="placa")
	private String placa;
	
	@Column(name="cilindraje")
	private int cilindraje;
	
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
	
	public TicketEntidad(Integer id, String tipo,String placa, int cilindraje, String estado, LocalDateTime fechaIngreso,LocalDateTime fechaSalida, BigDecimal valorPagar) {
		this.id = id;
		this.tipo = tipo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorPagar = valorPagar;
	}

	
	public Integer getId() {
		return id;
	}

	

	public String getTipo() {
		return tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public int getCilindraje() {
		return cilindraje;
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

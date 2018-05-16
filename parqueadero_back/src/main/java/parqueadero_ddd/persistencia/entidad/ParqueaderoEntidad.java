package parqueadero_ddd.persistencia.entidad;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="parqueadero")
public class ParqueaderoEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idVehiculo") 
	private VehiculoEntidad vehiculo;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="fechaIngreso")
	private LocalDateTime fechaIngreso;
	
	public ParqueaderoEntidad() {
		
	}
	
	public ParqueaderoEntidad(VehiculoEntidad vehiculo, String estado, LocalDateTime fechaIngreso) {
		this.vehiculo = vehiculo;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
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
	
	
	

}

package com.ceiba.parqueadero.persistencia.entidad;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="Parqueadero")
public class ParqueaderoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NonNull
	private LocalDateTime fechaIngreso;
	
	private String estado;
	
	@OneToOne(optional=false)
    @JoinColumn(name = "idVehiculo") 
	private VehiculoEntity vehiculoEntity;
	
	private LocalDateTime fechaSalida;

	public ParqueaderoEntity(Integer id, LocalDateTime fechaIngreso, String estado,VehiculoEntity vehiculoEntity,LocalDateTime fechaSalida) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
		this.vehiculoEntity = vehiculoEntity;
		this.fechaSalida = fechaSalida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public VehiculoEntity getVehiculoEntity() {
		return vehiculoEntity;
	}

	public void setVehiculoEntity(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
}

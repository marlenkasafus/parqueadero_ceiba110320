package com.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Vehiculo")
public class VehiculoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String tipoVehiculo;
	
	 @OneToOne(optional=false,cascade=CascadeType.ALL, 
		       mappedBy="vehiculoEntity",targetEntity=ParqueaderoEntity.class)
	private ParqueaderoEntity parqueaderoEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public ParqueaderoEntity getParqueaderoEntity() {
		return parqueaderoEntity;
	}

	public void setParqueaderoEntity(ParqueaderoEntity parqueaderoEntity) {
		this.parqueaderoEntity = parqueaderoEntity;
	}
	
	

}

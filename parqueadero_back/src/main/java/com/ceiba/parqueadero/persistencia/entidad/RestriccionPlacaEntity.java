package com.ceiba.parqueadero.persistencia.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RestriccionPlaca")
public class RestriccionPlacaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="placa")
	private char caracterPlaca;
	
	@ElementCollection
    private List<Integer> diasDeLaSemana = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public char getCaracterPlaca() {
		return caracterPlaca;
	}

	public void setCaracterPlaca(char caracterPlaca) {
		this.caracterPlaca = caracterPlaca;
	}

	public List<Integer> getDiasDeLaSemana() {
		return diasDeLaSemana;
	}

	public void setDiasDeLaSemana(List<Integer> diasDeLaSemana) {
		this.diasDeLaSemana = diasDeLaSemana;
	}
	
	

}

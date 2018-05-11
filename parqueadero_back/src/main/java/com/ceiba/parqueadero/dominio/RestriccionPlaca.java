package com.ceiba.parqueadero.dominio;

import java.util.ArrayList;
import java.util.List;

public class RestriccionPlaca {

	private char caracterPlaca;

	private List<Integer> diasDeLaSemana = new ArrayList<>();

	public RestriccionPlaca() {

	}

	public RestriccionPlaca(char caracterPlaca,List<Integer> diasDeLaSemana) {
		this.caracterPlaca = caracterPlaca;
		this.diasDeLaSemana = diasDeLaSemana;
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

package parqueadero_ddd.domain;

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

	public List<Integer> getDiasDeLaSemana() {
		return diasDeLaSemana;
	}
}

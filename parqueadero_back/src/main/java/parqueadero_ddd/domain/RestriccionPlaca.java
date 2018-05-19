package parqueadero_ddd.domain;

import java.util.List;

public class RestriccionPlaca {
	
	private char caracterPlaca;

	private List<Integer> diasDeLaSemana;

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

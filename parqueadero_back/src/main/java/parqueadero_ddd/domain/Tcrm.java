package parqueadero_ddd.domain;

import java.util.Calendar;

public class Tcrm {
	
	private String unidad;
	private Calendar validoDesde;
	private Calendar validoHasta;
	private Float valor;
	
	public Tcrm() {
		
	}
	
	public Tcrm(String unidad, Calendar validoDesde, Calendar validoHasta, Float valor) {
		this.unidad = unidad;
		this.validoDesde = validoDesde;
		this.validoHasta = validoHasta;
		this.valor = valor;
	}

	public String getUnidad() {
		return unidad;
	}

	public Calendar getValidoDesde() {
		return validoDesde;
	}

	public Calendar getValidoHasta() {
		return validoHasta;
	}

	public Float getValor() {
		return valor;
	}
}

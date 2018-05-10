package com.ceiba.parqueadero.dominio.enums;

import java.util.HashMap;

public enum TipoVehiculoEnum {
	
	CARRO("C","Carro"),
	MOTO("M","Moto");
	
	private String codigo;
	private String nombre;
	private static final HashMap<String, TipoVehiculoEnum> hashMapTipoVehiculoEnum = new HashMap<>();
	
	private TipoVehiculoEnum(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	static {
		for(TipoVehiculoEnum tipoVehiculoEnum: values()) {
			if (tipoVehiculoEnum.getCodigo() != null) {
				hashMapTipoVehiculoEnum.put(tipoVehiculoEnum.getCodigo(),tipoVehiculoEnum);
			}
		}
	}
	
	public static TipoVehiculoEnum getByCodigo(String codigo) {
		if (null == codigo) {
			return null;
		}
		return hashMapTipoVehiculoEnum.get(codigo);
	}

}

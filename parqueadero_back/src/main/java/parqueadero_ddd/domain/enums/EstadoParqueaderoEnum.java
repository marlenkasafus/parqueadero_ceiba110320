package parqueadero_ddd.domain.enums;

import java.util.HashMap;

public enum EstadoParqueaderoEnum {
	OCUPADO("O","Parqueadero ocupado"),
	LIBERADO("L","Parqueadero liberado");
	
	private String codigo;
	private String nombre;
	private static final HashMap<String, EstadoParqueaderoEnum> hashMapEstadoParqueaderoEnum = new HashMap<>();
	
	private EstadoParqueaderoEnum(String codigo, String nombre) {
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
		for(EstadoParqueaderoEnum estadoParqueaderoEnum: values()) {
			if (estadoParqueaderoEnum.getCodigo() != null) {
				hashMapEstadoParqueaderoEnum.put(estadoParqueaderoEnum.getCodigo(),estadoParqueaderoEnum);
			}
		}
	}
	
	public static EstadoParqueaderoEnum getByCodigo(String codigo) {
		if (null == codigo) {
			return null;
		}
		return hashMapEstadoParqueaderoEnum.get(codigo);
	}
}

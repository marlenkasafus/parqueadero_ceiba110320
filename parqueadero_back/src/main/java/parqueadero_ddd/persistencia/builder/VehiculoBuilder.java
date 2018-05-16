package parqueadero_ddd.persistencia.builder;

import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.persistencia.entidad.VehiculoEntidad;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {
	}

	public static VehiculoEntidad convertirAVehiculoEntidad(Vehiculo vehiculo) {
		return new VehiculoEntidad(vehiculo.getTipoVehiculoEnum().getCodigo(), vehiculo.getPlaca(), vehiculo.getCilindraje());
	}

}

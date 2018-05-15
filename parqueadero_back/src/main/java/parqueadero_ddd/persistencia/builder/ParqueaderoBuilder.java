package parqueadero_ddd.persistencia.builder;

import parqueadero_ddd.domain.ParqueaderoPOJO;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;
import parqueadero_ddd.persistencia.entidad.VehiculoEntidad;

public class ParqueaderoBuilder {

	private ParqueaderoBuilder() {
		
	}
	
	public static ParqueaderoEntidad convertirAParqueaderoEntidad(ParqueaderoPOJO parqueaderoPOJO) {
		VehiculoEntidad vehiculoEntidad = new VehiculoEntidad(parqueaderoPOJO.getVehiculo().getTipoVehiculoEnum().getCodigo(),parqueaderoPOJO.getVehiculo().getPlaca(),parqueaderoPOJO.getVehiculo().getCilindraje());
		return new ParqueaderoEntidad(vehiculoEntidad, parqueaderoPOJO.getEstadoParqueaderoEnum().getCodigo(),parqueaderoPOJO.getFechaIngreso());
	}

	public static ParqueaderoPOJO convertirAParqueaderoPOJO(ParqueaderoEntidad parqueaderoEntidad) {
		Vehiculo vehiculo = new Vehiculo(parqueaderoEntidad.getVehiculo().getPlaca(), TipoVehiculoEnum.getByCodigo(parqueaderoEntidad.getVehiculo().getTipo()),parqueaderoEntidad.getVehiculo().getCilindraje());
		return new ParqueaderoPOJO(vehiculo, parqueaderoEntidad.getFechaIngreso());
	}

}

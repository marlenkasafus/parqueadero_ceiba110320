package parqueadero_ddd.persistencia.builder;

import parqueadero_ddd.domain.ParqueaderoPOJO;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.entidad.ParqueaderoEntidad;

public class ParqueaderoBuilder {

	private ParqueaderoBuilder() {
		
	}
	
	public static ParqueaderoEntidad convertirAParqueaderoEntidad(ParqueaderoPOJO parqueaderoPOJO) {
		return new ParqueaderoEntidad(parqueaderoPOJO.getId(), VehiculoBuilder.convertirAVehiculoEntidad(parqueaderoPOJO.getVehiculo()), parqueaderoPOJO.getEstadoParqueaderoEnum().getCodigo(),parqueaderoPOJO.getFechaIngreso(),parqueaderoPOJO.getFechaSalida(),parqueaderoPOJO.getValorPagar());
	}

	public static ParqueaderoPOJO convertirAParqueaderoPOJO(ParqueaderoEntidad parqueaderoEntidad) {
		Vehiculo vehiculo = new Vehiculo(parqueaderoEntidad.getVehiculo().getPlaca(), TipoVehiculoEnum.getByCodigo(parqueaderoEntidad.getVehiculo().getTipo()),parqueaderoEntidad.getVehiculo().getCilindraje());
		return new ParqueaderoPOJO(parqueaderoEntidad.getId(),vehiculo, parqueaderoEntidad.getFechaIngreso(), parqueaderoEntidad.getFechaSalida(),parqueaderoEntidad.getValorPagar(),EstadoParqueaderoEnum.getByCodigo(parqueaderoEntidad.getEstado()));
	}

}

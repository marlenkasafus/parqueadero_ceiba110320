package parqueadero_ddd.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.repositorio.ParqueaderoRepositorio;

@Service
public class Parqueadero {
	
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	public Parqueadero(ParqueaderoRepositorio parqueaderoRepositorio) {
		this.parqueaderoRepositorio = parqueaderoRepositorio;
	}
	
	@Value("${parqueadero.max.carros}")
	private int cupoMaximoCarros;
	
	@Value("${parqueadero.max.motos}")
	private int cupoMaximoMotos;

	public int getCantidadCupos(TipoVehiculoEnum tipoVehiculo) {
		int cupos = 0;
		if (TipoVehiculoEnum.CARRO.equals(tipoVehiculo)) {
			cupos = cupoMaximoCarros;
		} else if (TipoVehiculoEnum.MOTO.equals(tipoVehiculo)) {
			cupos = cupoMaximoMotos;
		}
		return cupos;
	}

	public int getCantidadCeldasEnUso(TipoVehiculoEnum tipoVehiculo) {
		return parqueaderoRepositorio.getCantidadParqueaderosEnUsoPorVehiculo(tipoVehiculo);
	}

}

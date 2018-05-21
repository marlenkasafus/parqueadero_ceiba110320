package parqueadero_ddd.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.persistencia.repositorio.TicketRepositorio;

@Service
public class ParqueaderoConfiguracion {
	
	private TicketRepositorio ticketRepositorio;
	
	@Autowired
	public ParqueaderoConfiguracion(TicketRepositorio ticketRepositorio) {
		this.ticketRepositorio = ticketRepositorio;
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
		return ticketRepositorio.getCantidadParqueaderosEnUsoPorVehiculo(tipoVehiculo);
	}

}

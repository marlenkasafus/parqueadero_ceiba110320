package parqueadero_ddd.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.persistencia.repositorio.RestriccionPlacaRepositorio;

@Service
public class Calendario {
	
	@Autowired
	private RestriccionPlacaRepositorio restriccionPlacaRepositorio;
	
	private LocalDateTime fechaActual;
	
	public Calendario() {
		fechaActual = LocalDateTime.now();
	}
	
	

	public LocalDateTime getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(LocalDateTime fechaActual) {
		this.fechaActual = fechaActual;
	}

	public int obtenerDiaActual() {
		return fechaActual.getDayOfWeek().getValue();
	}
	
	public void esDiaHabilParaVehiculo(Vehiculo vehiculo) throws CalendarioException {
		if (TipoVehiculoEnum.CARRO.equals(vehiculo.getTipoVehiculoEnum())) {
			List<RestriccionPlaca> restriccionPlacas = restriccionPlacaRepositorio.findAll();
			char caracterPlacaVehiculo = vehiculo.obtenerCaracterInicialPlaca();
			int diaSemana = obtenerDiaActual();
			for (RestriccionPlaca restriccionPlaca : restriccionPlacas) {
				if (restriccionPlaca.getCaracterPlaca()==caracterPlacaVehiculo && restriccionPlaca.getDiasDeLaSemana().contains(diaSemana)) {
					throw new CalendarioException("No puede ingresar porque no está en un dia hábil");
				}
			}			
		}
	}
	
	

}

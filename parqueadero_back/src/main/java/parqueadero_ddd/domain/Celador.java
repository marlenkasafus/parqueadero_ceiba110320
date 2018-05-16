package parqueadero_ddd.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parqueadero_ddd.domain.enums.EstadoParqueaderoEnum;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;
import parqueadero_ddd.exception.CalendarioException;
import parqueadero_ddd.exception.CeladorException;
import parqueadero_ddd.persistencia.repositorio.ParqueaderoRepositorio;

@Service
public class Celador {
	
	private Calendario calendario;
	private Parqueadero parqueadero;
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	public Celador(Calendario calendario, Parqueadero parqueadero, ParqueaderoRepositorio parqueaderoRepositorio) {
		this.calendario = calendario;
		this.parqueadero = parqueadero;
		this.parqueaderoRepositorio = parqueaderoRepositorio;
	}
	
	public void hayEspaciosDisponibles(TipoVehiculoEnum tipoVehiculo) throws CeladorException {
		if (parqueadero.getCantidadCupos(tipoVehiculo)<=parqueadero.getCantidadCeldasEnUso(tipoVehiculo)) {
			throw new CeladorException("No hay celdas disponibles.");
		}
	}

	public ParqueaderoPOJO solicitudIngresoVehiculo(Vehiculo vehiculo) throws CeladorException, CalendarioException {
		hayEspaciosDisponibles(vehiculo.getTipoVehiculoEnum());
		calendario.esDiaHabilParaVehiculo(vehiculo);
		return generarIngresoVehiculo(vehiculo);
	}

	private ParqueaderoPOJO  generarIngresoVehiculo(Vehiculo vehiculo) {
		ParqueaderoPOJO parqueaderoPOJO = new ParqueaderoPOJO(vehiculo, calendario.getFechaActual());
		return parqueaderoRepositorio.save(parqueaderoPOJO);
	}

	public ParqueaderoPOJO solicitudRetiroVehiculo(ParqueaderoPOJO parqueaderoPOJO,LocalDateTime fechaSalida) throws CeladorException {
		parqueaderoPOJO = parqueaderoRepositorio.findById(parqueaderoPOJO.getId());
		if (parqueaderoPOJO == null) {
			throw new CeladorException("Ticket no encontrado, verifique el número e intente nuevamente");
		}
		parqueaderoPOJO.setFechaSalida(fechaSalida);
		calcularValorParqueo(parqueaderoPOJO);		
		return parqueaderoPOJO;
	}
	
	public ParqueaderoPOJO registrarRetiro(ParqueaderoPOJO parqueaderoPOJO) {
		parqueaderoPOJO.setEstadoParqueaderoEnum(EstadoParqueaderoEnum.LIBERADO);
		return parqueaderoRepositorio.save(parqueaderoPOJO);
	}
	

	public void calcularValorParqueo(ParqueaderoPOJO parqueaderoPOJO) {
		parqueaderoPOJO.setValorPagar(parqueaderoPOJO.getTicket().generarCobro(parqueaderoPOJO));
	}
	
	public List<ParqueaderoPOJO> consultarParqueaderosEnUso(){
		return parqueaderoRepositorio.getParqueaderosEnUso();
	}
	

}

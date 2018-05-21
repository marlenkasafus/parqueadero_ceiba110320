package parqueadero_ddd.domain.unit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

import parqueadero_ddd.domain.Ticket;
import parqueadero_ddd.domain.Vehiculo;
import parqueadero_ddd.domain.enums.TipoVehiculoEnum;

public class TicketTest {

	@Test
	public void calcularTiempoParqueoDosHoras() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 12, 24));
		long cantidadHoras = ticket.getCalculadora().calcularTiempoParqueo(ticket);
		assertEquals(2, cantidadHoras);
	}

	@Test
	public void calcularTiempoParqueoUnaHora() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 11, 24));
		long cantidadHoras = ticket.getCalculadora().calcularTiempoParqueo(ticket);
		assertEquals(1, cantidadHoras);
	}

	@Test
	public void calcularTiempoParqueoVeintiDosHoras() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 8, 24));
		long cantidadHoras = ticket.getCalculadora().calcularTiempoParqueo(ticket);
		assertEquals(22, cantidadHoras);
	}

	@Test
	public void calcularValorParqueoUnaHoraCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("1000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDosHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 13, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("2000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDiezHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 21, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("8000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoQuinceHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 2, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("8000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoVeintiCincoHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("9000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 17, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("14000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaYCincoHorasCarro() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.CARRO, 0);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 22, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("16000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoUnaHoraMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 220);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("500"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoUnaHoraMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 650);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("2500"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDosHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 100);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 13, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("1000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDosHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 510);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 13, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("3000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDiezHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 300);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 21, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("4000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoDiezHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 1200);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 15, 21, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("6000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoQuinceHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 125);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 2, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("4000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoQuinceHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 1125);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 2, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("6000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoVeintiCincoHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 150);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("4500"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoVeintiCincoHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 1250);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 12, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("6500"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 90);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 17, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("7000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 1190);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 17, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("9000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaYCincoHorasMotoBajoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 80);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 22, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("8000"), cantidadHoras);
	}

	@Test
	public void calcularValorParqueoTreintaYCincoHorasMotoAltoCilindraje() {
		Vehiculo vehiculo = new Vehiculo("AZY123", TipoVehiculoEnum.MOTO, 1180);
		Ticket ticket = new Ticket(null,vehiculo, LocalDateTime.of(2018, 5, 15, 11, 14),null,null,null);
		ticket.setFechaSalida(LocalDateTime.of(2018, 5, 16, 22, 10));
		BigDecimal cantidadHoras = ticket.getCalculadora().generarCobro(ticket);
		assertEquals(new BigDecimal("10000"), cantidadHoras);
	}

}

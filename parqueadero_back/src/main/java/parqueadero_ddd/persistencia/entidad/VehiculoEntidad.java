package parqueadero_ddd.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehiculo")
public class VehiculoEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="placa")
	private String placa;
	
	@Column(name="cilindraje")
	private int cilindraje;

	public VehiculoEntidad() {
	}
	public VehiculoEntidad(String tipo,String placa, int cilindraje) {
		this.tipo = tipo;
		this.placa = placa;
		this.cilindraje = cilindraje;
	}
	public String getTipo() {
		return tipo;
	}
	public String getPlaca() {
		return placa;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	
	
}

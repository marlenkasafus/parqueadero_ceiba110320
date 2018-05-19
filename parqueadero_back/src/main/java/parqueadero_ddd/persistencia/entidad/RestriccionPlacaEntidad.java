package parqueadero_ddd.persistencia.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RestriccionPlaca")
public class RestriccionPlacaEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="placa")
	private char caracterPlaca;
	
	@ElementCollection
    private List<Integer> diasDeLaSemana = new ArrayList<>();
	
	
	public char getCaracterPlaca() {
		return caracterPlaca;
	}

	public List<Integer> getDiasDeLaSemana() {
		return diasDeLaSemana;
	}
	
	

}

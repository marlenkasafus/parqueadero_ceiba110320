package parqueadero_ddd.persistencia.builder;

import parqueadero_ddd.domain.Tcrm;

public class TcrmBuilder {

	private TcrmBuilder() {
		
	}
	
	public static Tcrm convertirATcrm(superintendencia.webservice.Tcrm tcrmSuperintencencia) {
		return new Tcrm(tcrmSuperintencencia.getUnit(), tcrmSuperintencencia.getValidityFrom(), tcrmSuperintencencia.getValidityTo(), tcrmSuperintencencia.getValue());
	}

}

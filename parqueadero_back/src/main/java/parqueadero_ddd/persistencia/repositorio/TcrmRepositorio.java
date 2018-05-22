package parqueadero_ddd.persistencia.repositorio;

import java.rmi.RemoteException;

import org.springframework.stereotype.Repository;

import parqueadero_ddd.domain.Tcrm;
import parqueadero_ddd.persistencia.builder.TcrmBuilder;
import superintendencia.webservice.TCRMServicesInterfaceProxy;
import superintendencia.webservice.TcrmResponse;

@Repository
public class TcrmRepositorio {
	
	private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	
	public Tcrm getTcrm() throws RemoteException {
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		return TcrmBuilder.convertirATcrm(tcrmResponse);
	}

}

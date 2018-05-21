package parqueadero_ddd.service;

import java.rmi.RemoteException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import superintendencia.webservice.TCRMServicesInterfaceProxy;
import superintendencia.webservice.TcrmResponse;

@RestController
@RequestMapping("/TCRMService")
@CrossOrigin
public class TasaRepresentaticaMercadoService {
	
	private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<TcrmResponse> getTCRMActual() throws RemoteException{
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		return new ResponseEntity<>(tcrmResponse, HttpStatus.OK);
	}

}

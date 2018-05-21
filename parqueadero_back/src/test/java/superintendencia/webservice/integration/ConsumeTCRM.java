package superintendencia.webservice.integration;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;

import superintendencia.webservice.TCRMServicesInterfaceProxy;
import superintendencia.webservice.TcrmResponse;
public class ConsumeTCRM {
	
	/**
	 * Valid from and valid to TCRM date format
	 */
	private final static String _DATE_RESPONSE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
	
	/**
	 * TCRM query value format
	 */
	private final static String _VALUE_QUERY_FORMAT = "#0.00";
	
	/**
	 * Web Service end point
	 */
	private final static String _WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";


	@Test
	public void consumeTCRMTest() throws DatatypeConfigurationException, RemoteException {
		//
		// Simple date format declaration
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_DATE_RESPONSE_FORMAT);
		
		//
		// Decimal value format declaration
		DecimalFormat decimalFormat = new DecimalFormat(_VALUE_QUERY_FORMAT);
		
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(_WEB_SERVICE_URL);
				
		//
		// Gets the TCRM value for the current date
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		
		System.out.println("Identificador: " + tcrmResponse.getId());
		System.out.println("TCRM Valida desde: " + simpleDateFormat.format(tcrmResponse.getValidityFrom().getTime()));
		System.out.println("TCRM Valida hasta: " + simpleDateFormat.format(tcrmResponse.getValidityTo().getTime()));
		System.out.println("Valor: " + decimalFormat.format(tcrmResponse.getValue()));
	}

}

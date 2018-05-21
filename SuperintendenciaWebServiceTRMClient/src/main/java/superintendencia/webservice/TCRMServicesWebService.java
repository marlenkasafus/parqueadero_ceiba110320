package superintendencia.webservice;

public interface TCRMServicesWebService extends javax.xml.rpc.Service
{
	public java.lang.String getTCRMServicesWebServicePortAddress();

	public superintendencia.webservice.TCRMServicesInterface getTCRMServicesWebServicePort() throws javax.xml.rpc.ServiceException;

	public superintendencia.webservice.TCRMServicesInterface getTCRMServicesWebServicePort( java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

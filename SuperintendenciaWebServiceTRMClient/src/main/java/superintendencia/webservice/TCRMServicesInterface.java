package superintendencia.webservice;

public interface TCRMServicesInterface extends java.rmi.Remote 
{
    public superintendencia.webservice.TcrmResponse queryTCRM(java.util.Calendar tcrmQueryAssociatedDate) throws java.rmi.RemoteException;
}

package thi.iis.project.pruefungen.webservices;

public class DeadlineWebServiceProxy implements thi.iis.project.pruefungen.webservices.DeadlineWebService {
  private String _endpoint = null;
  private thi.iis.project.pruefungen.webservices.DeadlineWebService deadlineWebService = null;
  
  public DeadlineWebServiceProxy() {
    _initDeadlineWebServiceProxy();
  }
  
  public DeadlineWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDeadlineWebServiceProxy();
  }
  
  private void _initDeadlineWebServiceProxy() {
    try {
      deadlineWebService = (new thi.iis.project.pruefungen.webservices.DeadlineWebServiceServiceLocator()).getDeadlineWebServicePort();
      if (deadlineWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)deadlineWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)deadlineWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (deadlineWebService != null)
      ((javax.xml.rpc.Stub)deadlineWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public thi.iis.project.pruefungen.webservices.DeadlineWebService getDeadlineWebService() {
    if (deadlineWebService == null)
      _initDeadlineWebServiceProxy();
    return deadlineWebService;
  }
  
  public thi.iis.project.pruefungen.webservices.Deadline createDeadline(java.util.Calendar arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (deadlineWebService == null)
      _initDeadlineWebServiceProxy();
    return deadlineWebService.createDeadline(arg0, arg1);
  }
  
  
}
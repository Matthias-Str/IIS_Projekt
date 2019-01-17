package thi.iis.project.pruefungen.webservices;

public class ProfessorWebServiceProxy implements thi.iis.project.pruefungen.webservices.ProfessorWebService {
  private String _endpoint = null;
  private thi.iis.project.pruefungen.webservices.ProfessorWebService professorWebService = null;
  
  public ProfessorWebServiceProxy() {
    _initProfessorWebServiceProxy();
  }
  
  public ProfessorWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initProfessorWebServiceProxy();
  }
  
  private void _initProfessorWebServiceProxy() {
    try {
      professorWebService = (new thi.iis.project.pruefungen.webservices.ProfessorWebServiceServiceLocator()).getProfessorWebServicePort();
      if (professorWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)professorWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)professorWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (professorWebService != null)
      ((javax.xml.rpc.Stub)professorWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public thi.iis.project.pruefungen.webservices.ProfessorWebService getProfessorWebService() {
    if (professorWebService == null)
      _initProfessorWebServiceProxy();
    return professorWebService;
  }
  
  public thi.iis.project.pruefungen.webservices.Professor[] selectAll() throws java.rmi.RemoteException{
    if (professorWebService == null)
      _initProfessorWebServiceProxy();
    return professorWebService.selectAll();
  }
  
  public thi.iis.project.pruefungen.webservices.Professor selectById(int arg0) throws java.rmi.RemoteException{
    if (professorWebService == null)
      _initProfessorWebServiceProxy();
    return professorWebService.selectById(arg0);
  }
  
  
}
package thi.iis.project.pruefungen.webservices;

public class StudentWebServiceProxy implements thi.iis.project.pruefungen.webservices.StudentWebService {
  private String _endpoint = null;
  private thi.iis.project.pruefungen.webservices.StudentWebService studentWebService = null;
  
  public StudentWebServiceProxy() {
    _initStudentWebServiceProxy();
  }
  
  public StudentWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initStudentWebServiceProxy();
  }
  
  private void _initStudentWebServiceProxy() {
    try {
      studentWebService = (new thi.iis.project.pruefungen.webservices.StudentWebServiceServiceLocator()).getStudentWebServicePort();
      if (studentWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)studentWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)studentWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (studentWebService != null)
      ((javax.xml.rpc.Stub)studentWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public thi.iis.project.pruefungen.webservices.StudentWebService getStudentWebService() {
    if (studentWebService == null)
      _initStudentWebServiceProxy();
    return studentWebService;
  }
  
  public thi.iis.project.pruefungen.webservices.Student selectByRegistrationName(java.lang.String arg0) throws java.rmi.RemoteException{
    if (studentWebService == null)
      _initStudentWebServiceProxy();
    return studentWebService.selectByRegistrationName(arg0);
  }
  
  public thi.iis.project.pruefungen.webservices.Student[] selectAllStudents() throws java.rmi.RemoteException{
    if (studentWebService == null)
      _initStudentWebServiceProxy();
    return studentWebService.selectAllStudents();
  }
  
  
}
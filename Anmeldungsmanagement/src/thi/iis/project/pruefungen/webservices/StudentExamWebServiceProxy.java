package thi.iis.project.pruefungen.webservices;

public class StudentExamWebServiceProxy implements thi.iis.project.pruefungen.webservices.StudentExamWebService {
  private String _endpoint = null;
  private thi.iis.project.pruefungen.webservices.StudentExamWebService studentExamWebService = null;
  
  public StudentExamWebServiceProxy() {
    _initStudentExamWebServiceProxy();
  }
  
  public StudentExamWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initStudentExamWebServiceProxy();
  }
  
  private void _initStudentExamWebServiceProxy() {
    try {
      studentExamWebService = (new thi.iis.project.pruefungen.webservices.StudentExamWebServiceServiceLocator()).getStudentExamWebServicePort();
      if (studentExamWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)studentExamWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)studentExamWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (studentExamWebService != null)
      ((javax.xml.rpc.Stub)studentExamWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public thi.iis.project.pruefungen.webservices.StudentExamWebService getStudentExamWebService() {
    if (studentExamWebService == null)
      _initStudentExamWebServiceProxy();
    return studentExamWebService;
  }
  
  public void create(thi.iis.project.pruefungen.webservices.StudentExam arg0) throws java.rmi.RemoteException{
    if (studentExamWebService == null)
      _initStudentExamWebServiceProxy();
    studentExamWebService.create(arg0);
  }
  
  
}
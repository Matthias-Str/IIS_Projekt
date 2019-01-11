package thi.iis.project.pruefungen.webservices;

public class ExamWebServiceProxy implements thi.iis.project.pruefungen.webservices.ExamWebService {
  private String _endpoint = null;
  private thi.iis.project.pruefungen.webservices.ExamWebService examWebService = null;
  
  public ExamWebServiceProxy() {
    _initExamWebServiceProxy();
  }
  
  public ExamWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initExamWebServiceProxy();
  }
  
  private void _initExamWebServiceProxy() {
    try {
      examWebService = (new thi.iis.project.pruefungen.webservices.ExamWebServiceServiceLocator()).getExamWebServicePort();
      if (examWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)examWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)examWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (examWebService != null)
      ((javax.xml.rpc.Stub)examWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public thi.iis.project.pruefungen.webservices.ExamWebService getExamWebService() {
    if (examWebService == null)
      _initExamWebServiceProxy();
    return examWebService;
  }
  
  public thi.iis.project.pruefungen.webservices.Exam selectByName(java.lang.String arg0) throws java.rmi.RemoteException{
    if (examWebService == null)
      _initExamWebServiceProxy();
    return examWebService.selectByName(arg0);
  }
  
  public thi.iis.project.pruefungen.webservices.Exam[] selectAll() throws java.rmi.RemoteException{
    if (examWebService == null)
      _initExamWebServiceProxy();
    return examWebService.selectAll();
  }
  
  public java.util.Calendar getFirstExamDate() throws java.rmi.RemoteException{
    if (examWebService == null)
      _initExamWebServiceProxy();
    return examWebService.getFirstExamDate();
  }
  
  public thi.iis.project.pruefungen.webservices.Exam updateExamdate(java.lang.String arg0, java.util.Calendar arg1) throws java.rmi.RemoteException{
    if (examWebService == null)
      _initExamWebServiceProxy();
    return examWebService.updateExamdate(arg0, arg1);
  }
  
  
}
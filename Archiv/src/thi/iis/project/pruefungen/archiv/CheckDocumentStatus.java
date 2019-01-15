package thi.iis.project.pruefungen.archiv;

import java.rmi.RemoteException;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

/**
 * 
 * @author lars
 *
 */
public class CheckDocumentStatus {
    String registrationName;
    String examId;
    
    public CheckDocumentStatus() {
    
    }

    public CheckDocumentStatus(String registrationName, String examId) {
        super();
        this.registrationName = registrationName;
        this.examId = examId;
    }
    
    public StudentExam checkStatus() throws RemoteException{
        StudentExamWebService studentExamWs = new StudentExamWebServiceProxy().getStudentExamWebService();
        StudentExam studentExam = studentExamWs.selectByRegistrationNameAndExamId(registrationName, examId);
        return studentExam;
    }
}

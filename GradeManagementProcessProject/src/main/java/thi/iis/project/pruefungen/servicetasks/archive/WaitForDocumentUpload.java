package thi.iis.project.pruefungen.servicetasks.archive;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class WaitForDocumentUpload implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        StudentExamWebService StudentExamWS = new StudentExamWebServiceProxy().getStudentExamWebService();

        StudentExam studentExam = (StudentExam) execution.getVariable("element");
        String registrationName = studentExam.getRegistrationNumber().getRegistrationName();
        String examId = studentExam.getExamId().getExamId();

        System.out.println(registrationName + " " + examId);

        Boolean status = false;

        StudentExam se = StudentExamWS.selectByRegistrationNameAndExamId(registrationName, examId);
        status = se.getDocumentUploaded();
        System.out.println(status);
        execution.setVariableLocal("uploaded", String.valueOf(status));

    }

}

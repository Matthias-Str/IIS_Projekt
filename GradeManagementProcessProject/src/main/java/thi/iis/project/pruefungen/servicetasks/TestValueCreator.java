package thi.iis.project.pruefungen.servicetasks;

import java.util.Calendar;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

//MHoepp
public class TestValueCreator implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Calendar now = Calendar.getInstance();
        
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        StudentWebService studentService = new StudentWebServiceProxy().getStudentWebService();
        
        now.add(Calendar.MINUTE, 1);
        
        // set variable of endRegistrationTimer
        execution.setVariable("endRegistrationTimer", now.getTime());
        
        
        now.add(Calendar.MINUTE, 1);
        
        //First exam timer:
        execution.setVariable(ValueIdentifiers.TIMER_IDENTIFIER_FIRST_EXAM, now.getTime());
        
        examWS.updateExamdate("inf_m_iis_ws18", now);
        
        Student mih = studentService.selectByRegistrationName("michael");
        Student kak = studentService.selectByRegistrationName("katrin");
        
        Exam iisExam = examWS.selectByName("inf_m_iis_ws18");
        
        
        
        StudentExam mihSe = new StudentExam();
        mihSe.setRegistrationNumber(mih);
        mihSe.setExamId(iisExam);
        seWS.create(mihSe);
        
        StudentExam kakSe = new StudentExam();
        kakSe.setRegistrationNumber(kak);
        kakSe.setExamId(iisExam);
        seWS.create(kakSe);
        
        
        
        
    }

}

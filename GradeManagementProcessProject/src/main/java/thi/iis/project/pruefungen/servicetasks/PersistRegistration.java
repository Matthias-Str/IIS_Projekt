package thi.iis.project.pruefungen.servicetasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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


public class PersistRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get exam registrations
        String username = (String) execution.getVariable("username");
        Map<String, Boolean> examRegistrations = (HashMap<String, Boolean>) execution.getVariable("registrationList");

        // init webservices
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        StudentExamWebService stundetExamWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        // select user by registration name
        Student student = studentWS.selectByRegistrationName(username);
        
        for(Entry<String, Boolean> entry: examRegistrations.entrySet()){
            String examId = entry.getKey();
            Boolean participation = entry.getValue();
            
            // get exam
            Exam e = examWS.selectByName(examId);
            
            // add new entry in student_exam table if participation is true
            if(participation){
                StudentExam se = new StudentExam(false, e, null, false, false, student, 1);
                stundetExamWS.create(se);
            }
        }
        
    }



}

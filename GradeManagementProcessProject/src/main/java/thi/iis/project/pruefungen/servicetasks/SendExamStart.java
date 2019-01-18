package thi.iis.project.pruefungen.servicetasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Professor;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

//MHoepp
public class SendExamStart implements JavaDelegate
{
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Executing message: Send Exam Start. All variables: " );
        for(String varname : execution.getVariableNames())
        {
            Object obj = execution.getVariable(varname);
            if(obj!=null)
            {
                System.out.println(varname + "  ->  "+obj.toString());
            }else System.out.println(varname + "  -> NULL");
        }
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        Exam relevantExam = (Exam) execution.getVariable("element");
        String examName = relevantExam.getExamId();
        //ProfessorWebService professorWS = new ProfessorWebServiceProxy().getProfessorWebService();
        
        // get the assigned Professor via Database
        Professor prof = relevantExam.getProfessorId();
        
        // Username is firstname+lastname of prof
        String username = prof.getFirstname()+prof.getLastname();

        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        StudentExam[] allStudentExams = seWS.selectAll();
        
        List<StudentExam> studentexamlist = new ArrayList<StudentExam>();
        
        Map<String,Student> registeredStudents = new HashMap<String,Student>();
        for(StudentExam se : allStudentExams)
        {
            if(se.getExamId().equals(relevantExam))
            {
                registeredStudents.put(se.getRegistrationNumber().getRegistrationName(),se.getRegistrationNumber());
                studentexamlist.add(se);
            }
        }
        
        
        
        Map<String, Object> camundaVars = new HashMap<String, Object>();
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_EXAM_NAME, examName);
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_REGISTRATION_MAP, registeredStudents);
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_USERNAME, username);
        if(relevantExam.getDate()!=null)
        {
            camundaVars.put(ValueIdentifiers.TIMER_IDENTIFIER_EXAM_START, relevantExam.getDate().getTime());
        }else
        {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.SECOND, 30);
            camundaVars.put(ValueIdentifiers.TIMER_IDENTIFIER_EXAM_START, now.getTime());    //If date isn't set in the DB, just set the time to 30 secs from now
            
        }
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_EXAM_LIST, studentexamlist);


        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_SEND_EXAM_START)
                            .setVariables(camundaVars)
                            .correlateWithResult();

    }
    
    
}

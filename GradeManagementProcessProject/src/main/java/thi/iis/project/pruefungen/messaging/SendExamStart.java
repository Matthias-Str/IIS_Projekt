package thi.iis.project.pruefungen.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Professor;
import thi.iis.project.pruefungen.webservices.ProfessorWebService;
import thi.iis.project.pruefungen.webservices.ProfessorWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

public class SendExamStart implements JavaDelegate
{
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //get subject name, needs to be saved in DelegateExecution
        String examName = (String) execution.getVariable("examName");
        
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        Exam relevantExam = examWS.selectByName(examName);
        //ProfessorWebService professorWS = new ProfessorWebServiceProxy().getProfessorWebService();
        
        // get the assigned Professor via Database
        Professor prof = relevantExam.getProfessorId();
        // Username is mail adress of prof
        String username = prof.getMail();

        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        List<StudentExam> allStudentExams = seWS.selectAll();
        
        // TODO: Get the list of registeredStudents of specific subject
        Map<String,Student> registeredStudents = new HashMap<String,Student>();
        int index = (int) execution.getVariable("index");
        
        
        Map<String, Object> camundaVars = new HashMap<String, Object>();
        camundaVars.put("examName", examName);
        camundaVars.put("registeredStudents", registeredStudents);
        camundaVars.put("username", username);

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("sendExamStart")
                            .localVariableEquals("counter", Integer.valueOf(index).toString())
                            .setVariables(camundaVars)
                            .correlateWithResult();

    }
    
}

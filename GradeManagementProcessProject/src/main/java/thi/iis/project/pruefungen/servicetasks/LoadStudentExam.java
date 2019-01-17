package thi.iis.project.pruefungen.servicetasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class LoadStudentExam implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution ) throws Exception
    {
        
        //Map<String,Student> registeredStudents = (Map<String,Student>) execution.getVariable("registeredStudents");
        List<StudentExam> studentexamlist = (List<StudentExam>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_EXAM_LIST);
        
        StudentExam studentexam = (StudentExam) execution.getVariable("element");
        
        Student relevantStudent = studentexam.getRegistrationNumber();
        
        Map<String, Object> camundaVars = new HashMap<String, Object>();
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT, relevantStudent);
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_EXAM, studentexam);
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_NAME, relevantStudent.getRegistrationName());


        execution.setVariables(camundaVars);
    }

}

package thi.iis.project.pruefungen.servicetasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class TestGetStudentExams implements JavaDelegate{

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        StudentExam[] seList = seWS.selectAll();
        List<StudentExam> seArray = new ArrayList<>();
        seArray = Arrays.asList(seList);
        
        execution.setVariable("studentExamList", seArray);
    }

}

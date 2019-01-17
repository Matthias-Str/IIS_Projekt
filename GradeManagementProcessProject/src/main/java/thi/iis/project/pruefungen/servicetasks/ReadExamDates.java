package thi.iis.project.pruefungen.servicetasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

//MHoepp
public class ReadExamDates implements JavaDelegate
{

    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();

        execution.setVariable(ValueIdentifiers.VALUE_IDENTIFIER_EXAM_COUNT, examWS.selectAll().length);
        execution.setVariable(ValueIdentifiers.VALUE_IDENTIFIER_ALL_EXAMS, new ArrayList<Exam>(Arrays.asList(examWS.selectAll()) ));
        
    }
    
}

package thi.iis.project.pruefungen.servicetasks.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.servicetasks.ValueIdentifiers;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class ExamParticipation implements JavaDelegate
{

    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Converting participation form results to process variable for "+execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_USERNAME));
        
        //Conversion no longer required
        
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_EXAM_PARTICIPATION)
                            .correlateWithResult();
        System.out.println("Conversion complete, sent out new message");
    }
    
}

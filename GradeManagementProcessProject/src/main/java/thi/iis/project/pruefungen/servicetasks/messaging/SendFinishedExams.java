package thi.iis.project.pruefungen.servicetasks.messaging;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.servicetasks.ValueIdentifiers;

public class SendFinishedExams implements JavaDelegate 
{
    

    @Override
    public void execute(DelegateExecution execution) throws Exception 
    {
        System.out.println("Preparing to send Finished Exams message");
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_EXAM_FINISHED)
        .correlateWithResult();
        System.out.println("Successfully sent Finished Exams message");
    }
    

}

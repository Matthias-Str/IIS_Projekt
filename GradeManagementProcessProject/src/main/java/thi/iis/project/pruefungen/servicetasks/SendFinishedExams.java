package thi.iis.project.pruefungen.servicetasks;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendFinishedExams implements JavaDelegate 
{
    

    @Override
    public void execute(DelegateExecution execution) throws Exception 
    {
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_EXAM_FINISHED)
        .correlateWithResult();
    }
    

}

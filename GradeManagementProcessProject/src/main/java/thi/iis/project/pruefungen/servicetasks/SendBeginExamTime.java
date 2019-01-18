package thi.iis.project.pruefungen.servicetasks;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendBeginExamTime implements JavaDelegate
{

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> camundaVars = new HashMap<String, Object>();

        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_BEGIN_EXAM_TIME)
                            .setVariables(camundaVars)
                            .correlateWithResult();
    }
    
}

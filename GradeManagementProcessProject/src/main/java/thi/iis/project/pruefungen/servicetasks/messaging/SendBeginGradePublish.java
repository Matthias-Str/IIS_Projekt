package thi.iis.project.pruefungen.servicetasks.messaging;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.servicetasks.ValueIdentifiers;

public class SendBeginGradePublish implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception 
    {
        Map<String, Object> camundaVars = new HashMap<String, Object>();

        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_EXAM_COUNT, execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_EXAM_COUNT));
        camundaVars.put(ValueIdentifiers.VALUE_IDENTIFIER_ANNOUCEMENT_DATE, execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_ANNOUCEMENT_DATE));
        
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation(ValueIdentifiers.MESSAGE_IDENTIFIER_BEGIN_GRADE_PUBLISH)
                            .setVariables(camundaVars)
                            .correlateWithResult();
        
    }

}

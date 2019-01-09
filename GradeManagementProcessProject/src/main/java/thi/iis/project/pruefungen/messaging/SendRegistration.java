package thi.iis.project.pruefungen.messaging;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.MessageCorrelationResultType;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import thi.iis.project.pruefungen.bean.Anmeldung;

public class SendRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String product = "Auto";
        int quantity = 3;
        String comment = "test";
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("product", product);
        data.put("quantity", quantity);
        data.put("comment", comment);
        
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        MessageCorrelationResult mcresult = runtimeService.createMessageCorrelation("NewOrderMessage")
                                                            .setVariables(data)
                                                            .correlateWithResult();
        
        if (mcresult.getResultType() == MessageCorrelationResultType.Execution) {
            Execution exec = mcresult.getExecution();
            System.out.println("Waiting process with ProcessInstanceId " + exec.getProcessInstanceId() + " was continued!");
        }
        else {
            ProcessInstance processInstance = mcresult.getProcessInstance();
            System.out.println("New process with ProcessInstanceId " + processInstance.getProcessInstanceId() + " was started!");
        }
//        
//        Boolean sesa = (Boolean) execution.getVariable("participition_sesa");
//        Boolean kao = (Boolean) execution.getVariable("participition_kao");
//        Boolean iis = (Boolean) execution.getVariable("participition_iis");
//        Boolean itim = (Boolean) execution.getVariable("participition_itim");
//
//        String username = "katrin";
//
//        Anmeldung registration = new Anmeldung(username, sesa, kao, iis, itim);
//        
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("registration", registration);
//        
//        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
//        MessageCorrelationResult mcresult = runtimeService.createMessageCorrelation("newRegistration")
//                                                            .
//                                                            .setVariables(data)
//                                                            .correlateWithResult();
//        
//        if (mcresult.getResultType() == MessageCorrelationResultType.Execution) {
//            Execution exec = mcresult.getExecution();
//            System.out.println("Waiting process with ProcessInstanceId " + exec.getProcessInstanceId() + " was continued!");
//        }
//        else {
//            ProcessInstance processInstance = mcresult.getProcessInstance();
//            System.out.println("New process with ProcessInstanceId " + processInstance.getProcessInstanceId() + " was started!");
//        }
    }



}

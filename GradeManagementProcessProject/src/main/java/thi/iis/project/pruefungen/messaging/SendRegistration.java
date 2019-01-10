package thi.iis.project.pruefungen.messaging;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get exam registrations
        String username = (String) execution.getVariable("username");
        int index = (int) execution.getVariable("index");
        
        Boolean sesa = (Boolean) execution.getVariable("inf_m_sesa_ws18");
        Boolean kao = (Boolean) execution.getVariable("inf_m_kao_ws18");
        Boolean iis = (Boolean) execution.getVariable("inf_m_iis_ws18");
        Boolean itim = (Boolean) execution.getVariable("inf_m_itim_ws18");
        Map<String, Boolean> registrationList = new HashMap<>();
        registrationList.put("inf_m_sesa_ws18", sesa);
        registrationList.put("inf_m_kao_ws18", kao);
        registrationList.put("inf_m_iis_ws18", iis);
        registrationList.put("inf_m_itim_ws18", itim);
        
        Map<String, Object> examRegistration = new HashMap<String, Object>();
        examRegistration.put("username", username);
        examRegistration.put("registrationList", registrationList);

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("sendRegistration")
                            .localVariableEquals("counter", Integer.valueOf(index).toString())
                            .setVariables(examRegistration)
                            .correlateWithResult();

    }

}

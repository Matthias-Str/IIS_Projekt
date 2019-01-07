package thi.iis.project.pruefungen.servicetasks;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Sending notification that exam registration starts soon
 * @author Katrin Krüger
 *
 */
public class SendRegistrationNotification implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
                
        RuntimeService rs = execution.getProcessEngineServices().getRuntimeService();
        // startRegistration = Name, auf den empfangender Task reagiert
        rs.startProcessInstanceByMessage("startRegistration");
     }    

}

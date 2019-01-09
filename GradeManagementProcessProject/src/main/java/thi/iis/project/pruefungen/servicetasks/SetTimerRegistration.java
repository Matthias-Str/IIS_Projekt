package thi.iis.project.pruefungen.servicetasks;

import java.util.Calendar;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Deadline;
import thi.iis.project.pruefungen.webservices.DeadlineWebService;
import thi.iis.project.pruefungen.webservices.DeadlineWebServiceProxy;

/**
 * Setting timer for Registration Progress
 * 
 * @author Katrin Krüger
 *
 */
public class SetTimerRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // init new webservices
        DeadlineWebService deadlineWS = new DeadlineWebServiceProxy().getDeadlineWebService();
        
        // select start_registration date from database
        Calendar startRegistration = Calendar.getInstance();
        Deadline d = deadlineWS.selectDeadlineByName("start_registration");
        startRegistration = d.getDate();
        
        // now set timer values
        execution.setVariable("startRegistrationTimer", startRegistration.getTime());

    }

}

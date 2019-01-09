package thi.iis.project.pruefungen.messaging;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SelectBuilder;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Deadline;
import thi.iis.project.pruefungen.webservices.DeadlineWebService;
import thi.iis.project.pruefungen.webservices.DeadlineWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

/**
 * Sending notification that exam registration starts soon
 * 
 * @author Katrin Krüger
 *
 */
public class SendRegistrationNotification implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get start end enddate of registration
        DeadlineWebService deadlineWS = new DeadlineWebServiceProxy().getDeadlineWebService();

        Calendar startRegistration = Calendar.getInstance();
        Deadline start = deadlineWS.selectDeadlineByName("start_registration");
        startRegistration = start.getDate();

        Calendar endRegistration = Calendar.getInstance();
        Deadline end = deadlineWS.selectDeadlineByName("end_registration");
        endRegistration = end.getDate();

        // select all examdates
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        Exam kao = examWS.selectByName("inf_m_kao_ws18");
        Exam iis = examWS.selectByName("inf_m_iis_ws18");
        Exam sesa = examWS.selectByName("inf_m_sesa_ws18");
        Exam itim = examWS.selectByName("inf_m_itim_ws18");

        Exam[] examList = examWS.selectAll();
        
        // put all necessarx data into map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("startRegistrationTimer", startRegistration.getTime());
        data.put("endRegistrationTimer", endRegistration.getTime());
        data.put("examList", examList);
        data.put("kao", kao);
        data.put("iis", iis);
        data.put("sesa", sesa);
        data.put("itim", itim);

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("startRegistration").setVariables(data).correlateWithResult();

    }

}

package thi.iis.project.pruefungen.servicetasks;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.DeadlineWebService;
import thi.iis.project.pruefungen.webservices.DeadlineWebServiceProxy;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;

/**
 * Servicetask to persist deadlines and examdates
 * @author Katrin Krüger
 *
 */
public class CallCreateDeadlineWS implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // init new webservices
        DeadlineWebService deadlineWS = new DeadlineWebServiceProxy().getDeadlineWebService();
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        
        // extract deadlines
        String start_registration = (String) execution.getVariable("start_registration");
        String end_registration = (String) execution.getVariable("end_registration");
        String grade_registration = (String) execution.getVariable("grade_registration");
        String announcement_date = (String) execution.getVariable("announcement_date");
        
        // extract examdates 
        String examdate_kao = (String) execution.getVariable("examdate_kao");
        String examdate_iis = (String) execution.getVariable("examdate_iis");
        String examdate_sesa = (String) execution.getVariable("examdate_sesa");
        String examdate_itim = (String) execution.getVariable("examdate_itim");
        
        // init deadline, calendar and formatter    
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
        
        // store deadlines
        //start registration
        cal.setTime(sdf.parse(start_registration));
        deadlineWS.createDeadline(cal, "start_registration");
        //end_registration
        cal.setTime(sdf.parse(end_registration));
        deadlineWS.createDeadline(cal, "end_registration");
        //grade_registration
        cal.setTime(sdf.parse(grade_registration));
        deadlineWS.createDeadline(cal, "grade_registration");
        //announcement_date
        cal.setTime(sdf.parse(announcement_date));
        deadlineWS.createDeadline(cal, "announcement_date");
        
        // store examdates
        // itim
        cal.setTime(sdf.parse(examdate_itim));
        examWS.updateExamdate("inf_m_itim_ws18", cal);
        //sesa
        cal.setTime(sdf.parse(examdate_sesa));
        examWS.updateExamdate("inf_m_sesa_ws18", cal);
        //iis
        cal.setTime(sdf.parse(examdate_iis));
        examWS.updateExamdate("inf_m_iis_ws18", cal);
        //kao
        cal.setTime(sdf.parse(examdate_kao));
        examWS.updateExamdate("inf_m_kao_ws18", cal);
        
        
    }

}

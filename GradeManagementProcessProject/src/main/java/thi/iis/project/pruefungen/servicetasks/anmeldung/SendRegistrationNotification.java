package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.beans.InputData;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;

/**
 * Sending notification that exam registration starts soon
 * 
 * @author Katrin Krüger
 *
 */
public class SendRegistrationNotification implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get current index and select user from studentList
        int index = (int) execution.getVariable("loopCounter");
        InputData inputData = (InputData) execution.getVariable("inputData");
        Student[] studentList = inputData.getStudentList();
        
        Student curStudent = studentList[index];
        
        Exam[] examList = inputData.getExamList();
        
        // put all necessary data into map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("start_registration", (Date) execution.getVariable("start_registration"));
        data.put("end_registration", (Date) execution.getVariable("end_registration"));
        data.put("index", index);
        for(Exam e : examList){
            data.put(e.getExamId(), e.getDate().getTime());

        }

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("startRegistration").setVariables(data).correlateWithResult();

        // date for seconde message
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("firstExamDate", (Date) execution.getVariable("firstExamDate"));
        data2.put("grade_registration", (Date) execution.getVariable("grade_registration"));

        // correlate message "startDocumentListener"
        runtimeService.createMessageCorrelation("startDocumentListener").setVariables(data2).correlateWithResult();

    }

}

package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.pojos.ExamList;
import thi.iis.project.pruefungen.pojos.StudentList;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;

/**
 * implementation of task "AnmeldungsNotification schicken", Sending
 * notification that exam registration starts soon
 * 
 * @author Katrin Krüger
 *
 */
public class SendRegistrationNotification implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // get current index and select user from studentList
        int index = (int) execution.getVariable("loopCounter");
        String studentString = (String) execution.getVariable("studentList");
        JSONObject studentJSON = new JSONObject(studentString);
        StudentList studentL = new StudentList();
        studentL.setStudentFromJson(studentJSON);
        Student[] studentList = studentL.getStudents();
        Student curStudent = studentList[index];
        String username = curStudent.getRegistrationName();

        // put all necessary data into map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("start_registration", (Date) execution.getVariable("start_registration"));
        data.put("end_registration", (Date) execution.getVariable("end_registration"));
        data.put("student", curStudent);
        data.put("username", username);
        // get exams
        String examString = (String) execution.getVariable("examList");
        JSONObject examJSON = new JSONObject(examString);
        ExamList examL = new ExamList();
        examL.setExamsFromJson(examJSON);
        Exam[] examList = examL.getExams();
        for (Exam e : examList) {
            data.put(e.getExamId(), e);

        }

        // correlate message "startRegistration"
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("startRegistration").setVariables(data).correlateWithResult();

    }

}

package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;

import org.camunda.bpm.engine.impl.util.json.JSONObject;

/**
 * Class represents a request that is send to check if document was uploaded
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    String processId;
    String registrationName;
    String examId;

    public DocumentStatusRequest() {
        super();
    }



    public DocumentStatusRequest(String processId, String registrationName, String examId) {
        super();
        this.processId = processId;
        this.registrationName = registrationName;
        this.examId = examId;
    }



    public String getRegistrationName() {
        return registrationName;
    }

    public void setRegistrationName(String registrationName) {
        this.registrationName = registrationName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }



    public String getProcessId() {
        return processId;
    }



    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();
        
        result.put("processId", processId);
        result.put("examId", examId);
        result.put("registrationName", registrationName);
        
        return result;
    }
    
}

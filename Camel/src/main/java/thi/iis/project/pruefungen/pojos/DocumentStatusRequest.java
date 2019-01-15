package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;

import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class DocumentStatusRequest implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String registrationName;
    String examId;
    
    public DocumentStatusRequest() {
        super();
    }
    
    public DocumentStatusRequest(String registrationName, String examId) {
        super();
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

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("registrationName", registrationName);
        object.put("examId", examId);
        return object;
    }
}

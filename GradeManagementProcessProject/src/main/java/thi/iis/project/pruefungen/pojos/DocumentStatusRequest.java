package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;

/**
 * Class represents a request that is send to check if document was uploaded
 * 
 * @author Katrin Krüger
 *
 */
public class DocumentStatusRequest implements Serializable {
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

}

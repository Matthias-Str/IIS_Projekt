package thi.iis.project.pruefungen.pojos;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.json.JSONArray;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class RegistrationRequestReply implements Serializable {

    private static final long serialVersionUID = 1L;

    String examId;
    StudentExam[] studentExamList;

    public RegistrationRequestReply() {
        super();
    }

    public RegistrationRequestReply(String examId, StudentExam[] studentExamList) {
        super();
        this.examId = examId;
        this.studentExamList = studentExamList;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public StudentExam[] getStudentExamList() {
        return studentExamList;
    }

    public void setStudentExamList(StudentExam[] studentExamList) {
        this.studentExamList = studentExamList;
    }

    public JSONObject studentExamListToJson() {
        JSONObject result = new JSONObject();

        JSONArray data = new JSONArray();
        if (studentExamList.length != 0) {
            for (StudentExam se : studentExamList) {
                data.add(se.toJson());
            }
        }

        try {
            result.put("studentExams", data);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}

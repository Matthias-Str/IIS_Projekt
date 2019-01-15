package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.webservices.Exam;

/**
 * represents list of exams that is send as xml
 * 
 * @author Katrin Krüger
 *
 */
@XmlRootElement
public class ExamList {
    Exam[] exams;

    public ExamList() {

    }

    @XmlElement(name = "exams")
    public Exam[] getExams() {
        return exams;
    }

    public void setExams(Exam[] exams) {
        this.exams = exams;
    }

    public void setExamsFromJson(JSONObject jsonObject) {
        JSONArray examArray = (JSONArray) jsonObject.get("exams");
        exams = new Exam[examArray.length()];
        for (int i = 0; i < examArray.length(); i++) {
            Exam e = new Exam();
            e = e.fromJson((JSONObject) examArray.get(i));
            exams[i] = e;
        }
    }
}

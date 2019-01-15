package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import thi.iis.project.pruefungen.webservices.Exam;

/**
 * List with exams that is send as xml
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

    public JSONObject toJson() {
        JSONObject result = new JSONObject();

        JSONArray data = new JSONArray();
        for (Exam e : exams) {
            data.add(e.toJson());
        }

        result.put("exams", data);

        return result;
    }
}

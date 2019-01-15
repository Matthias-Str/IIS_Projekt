package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import thi.iis.project.pruefungen.webservices.Student;

/**
 * List of students that is send as xml
 * 
 * @author Katrin Krüger
 *
 */
@XmlRootElement
public class StudentList {
    Student[] students;

    public StudentList() {

    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();

        JSONArray data = new JSONArray();
        for (Student s : students) {
            data.add(s.toJson());
        }

        result.put("students", data);

        return result;
    }

}

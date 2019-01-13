package thi.iis.project.pruefungen.pojos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import thi.iis.project.pruefungen.webservices.Student;

@XmlRootElement
public class StudentList {
    Student[] students;
    
    public StudentList(){
        
    }

    @XmlElement(name="students")
    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
    
    public void setStudentFromJson(JSONObject jsonObject){
        JSONArray studentArray = (JSONArray) jsonObject.get("students");
        students = new Student[studentArray.length()];
        for(int i = 0; i < studentArray.length(); i++){
            Student s = new Student();
            s = s.fromJson((JSONObject) studentArray.get(i));
            students[i] = s;
        }
    }
    
}

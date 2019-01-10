package thi.iis.project.pruefungen.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.Student;
import thi.iis.project.pruefungen.jpa.services.StudentService;

@WebService
public class StudentWebService {

    @Inject
    StudentService studentService;
    
    public List<Student> selectAllStudents(){
        return studentService.selectAll();
    }
    
    public Student selectByRegistrationName(String registrationName){
        return studentService.selectByRegistrationName(registrationName);
    }
}

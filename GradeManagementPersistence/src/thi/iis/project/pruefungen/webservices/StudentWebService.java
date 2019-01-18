package thi.iis.project.pruefungen.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.Student;
import thi.iis.project.pruefungen.jpa.services.StudentService;

/**
 * WebService for Student Modifications
 * 
 * @author Katrin Krüger
 *
 */
@WebService
public class StudentWebService {

    @Inject
    StudentService studentService;

    /**
     * select all students
     * 
     * @return List<Student>
     */
    public List<Student> selectAllStudents() {
        return studentService.selectAll();
    }

    /**
     * select a student by ist registrationName
     * 
     * @param registrationName
     * @return Student
     */
    public Student selectByRegistrationName(String registrationName) {
        return studentService.selectByRegistrationName(registrationName);
    }
    

}

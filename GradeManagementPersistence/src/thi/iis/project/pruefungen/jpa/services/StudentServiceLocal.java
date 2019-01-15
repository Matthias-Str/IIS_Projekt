package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Student;

/**
 * Local Service Interface for Student Service
 * 
 * @author Katrin Krüger
 *
 */
@Local
public interface StudentServiceLocal {

    /**
     * select all students
     * 
     * @return List<Student>
     */
    public List<Student> selectAll();

    /**
     * select student by registration name
     * 
     * @param registrationName
     * @return Student
     */
    public Student selectByRegistrationName(String registrationName);
}

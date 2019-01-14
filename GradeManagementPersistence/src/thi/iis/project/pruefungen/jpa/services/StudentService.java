package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Student;

/**
 * Session Bean implementation class StudentService
 * @author Katrin Krüger
 */
@Stateless
@LocalBean
public class StudentService implements StudentServiceLocal {

    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public StudentService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Student> selectAll() {        
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public Student selectByRegistrationName(String registrationName) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE registration_name = :name", Student.class);
        query.setParameter("name", registrationName);
        return query.getSingleResult();
    }
    
    

}

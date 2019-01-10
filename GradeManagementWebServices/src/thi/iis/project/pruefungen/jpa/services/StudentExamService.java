package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.StudentExam;

/**
 * Session Bean implementation class StudentExamSSService
 */
@Stateless
@LocalBean
public class StudentExamService implements StudentExamServiceLocal {
    
    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public StudentExamService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void create(StudentExam se) {
        em.merge(se);
    }
}

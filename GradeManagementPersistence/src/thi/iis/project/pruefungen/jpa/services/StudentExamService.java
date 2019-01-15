package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import thi.iis.project.pruefungen.jpa.entities.StudentExam;

/**
 * Session Bean implementation class StudentExamService
 * 
 * @author Katrin Krüger
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

    /**
     * create new StudentExam
     * 
     * @param studentExam
     * @return
     */
    @Override
    public void create(StudentExam studentExam) {
        em.merge(studentExam);
    }
}

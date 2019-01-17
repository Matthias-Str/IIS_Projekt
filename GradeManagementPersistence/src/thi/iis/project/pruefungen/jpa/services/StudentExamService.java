package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.Student;
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
    
    
    //MHoepp
    @Override
    public List<StudentExam> selectAll() {        
        TypedQuery<StudentExam> query = em.createQuery("SELECT s FROM StudentExam s", StudentExam.class);
        return query.getResultList();
    }

    @Override
    public void create(StudentExam se) {
        em.merge(se);
    }

    //MHoepp
    @Override
    public List<StudentExam> selectFromExam(Exam ex)
    {
        TypedQuery<StudentExam> query = em.createQuery("SELECT s FROM StudentExam s WHERE exam_id = :id", StudentExam.class);
        query.setParameter("id", ex.getExamId());
        return query.getResultList();
    }
}

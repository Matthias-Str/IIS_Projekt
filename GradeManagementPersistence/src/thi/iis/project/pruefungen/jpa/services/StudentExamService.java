package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;
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
    
    @Override
    public List<StudentExam> selectByExamId(String examId){
        TypedQuery<StudentExam> query = em.createQuery("SELECT se FROM StudentExam se WHERE exam_id like :id", StudentExam.class);
        query.setParameter("id", examId);
        return query.getResultList();
    }
    
    @Override
    public StudentExam selectByRegistrationNameAndExamId(String registrationName, String examId){
        TypedQuery<StudentExam> query = em.createQuery("SELECT se FROM StudentExam se WHERE exam_id like :id AND registration_name like :name", StudentExam.class);
        query.setMaxResults(1);
        query.setParameter("id", examId);
        query.setParameter("name", registrationName);
        return query.getSingleResult();
    }
    
    @Override
    public void update(StudentExam se){
        em.flush();
        em.merge(se);
    }
}

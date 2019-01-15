package thi.iis.project.pruefungen.jpa.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;

/**
 * Session Bean implementation class ExamServiceLocal
 * 
 * @author Katrin Krüger
 */
@Stateless
@LocalBean
public class ExamService implements ExamServiceLocal {
    @PersistenceContext
    EntityManager em;

    /**
     * update existing exam
     * 
     * @param Exam
     * @return Exam
     */
    @Override
    public Exam update(Exam e) {
        em.flush();
        em.merge(e);
        return e;
    }

    /**
     * selct exam by name
     * 
     * @param name
     * @return Exam
     */
    @Override
    public Exam selectByName(String name) {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e WHERE exam_id = :id", Exam.class);
        query.setParameter("id", name);
        return query.getSingleResult();
    }

    /**
     * select all exams
     * @return List<Exam>
     */
    @Override
    public List<Exam> selectAll() {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e", Exam.class);
        return query.getResultList();
    }

    /**
     * get frist exam date
     * 
     * @return java.util.Date
     */
    @Override
    public Date getFirstExamDate() {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e ORDER BY e.date asc", Exam.class);
        query.setMaxResults(1);
        Exam e = query.getSingleResult();
        return e.getDate();
    }
}

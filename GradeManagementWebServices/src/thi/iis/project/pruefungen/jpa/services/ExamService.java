package thi.iis.project.pruefungen.jpa.services;

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

    @Override
    public Exam update(Exam e) {
        em.flush();
        em.merge(e);
        return e;
    }

    @Override
    public Exam selectByName(String name) {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e WHERE exam_id = :id", Exam.class);
        query.setParameter("id", name);
        return query.getSingleResult();
    }
}

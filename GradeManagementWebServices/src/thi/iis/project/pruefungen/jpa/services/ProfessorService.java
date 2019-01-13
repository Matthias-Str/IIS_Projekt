package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.Professor;

/**
 * Session Bean implementation class ProfessorService
 */
@Stateless
@LocalBean
public class ProfessorService {

    @PersistenceContext
    EntityManager em;
    
 
    public Professor update(Professor p) {
        em.flush();
        em.merge(p);
        return p;
    }

    public Professor selectById(int id) {
        TypedQuery<Professor> query = em.createQuery("SELECT e FROM Professor e WHERE professor_id = :id", Professor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Professor> selectAll() {
        TypedQuery<Professor> query = em.createQuery("SELECT e FROM Professor e", Professor.class);
        return query.getResultList();
    }

}

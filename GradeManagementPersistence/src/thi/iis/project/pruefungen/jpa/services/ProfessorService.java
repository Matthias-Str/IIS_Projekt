package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.Professor;

//MHoepp
@Stateless
@LocalBean
public class ProfessorService
{


    public ProfessorService()
    {
        
    }
    
    @PersistenceContext
    EntityManager em;

    public Professor update(Professor e) 
    {
        em.flush();
        em.merge(e);
        return e;
    }

    public Professor selectById(int id) 
    {
        TypedQuery<Professor> query = em.createQuery("SELECT e FROM Professor e WHERE professor_id = :id", Professor.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Professor> selectAll() 
    {
        TypedQuery<Professor> query = em.createQuery("SELECT e FROM Professor e", Professor.class);
        return query.getResultList();
    }
    
    

}

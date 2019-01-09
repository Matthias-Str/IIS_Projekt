package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Deadline;
import thi.iis.project.pruefungen.jpa.entities.Exam;

/**
 * Session Bean implementation class DeadlineService
 * @author Katrin Krüger
 */
@Stateless
@LocalBean
public class DeadlineService implements DeadlineServiceLocal {

    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public DeadlineService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void create(Deadline d) {
        em.persist(d);
    }
    
    @Override
    public Deadline selectByName(String name) {
        TypedQuery<Deadline> query = em.createQuery("SELECT d FROM Deadline d WHERE deadline_name = :name", Deadline.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}

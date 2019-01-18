package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thi.iis.project.pruefungen.jpa.entities.Deadline;

/**
 * Session Bean implementation class DeadlineService
 * 
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

    /**
     * create new Deadline
     * 
     * @param deadline
     */
    @Override
    public void create(Deadline deadline) {
        em.persist(deadline);
    }

    /**
     * select a deadline by name
     * 
     * @param name
     * @return Deadline
     */
    @Override
    public Deadline selectByName(String name) {
        TypedQuery<Deadline> query = em.createQuery("SELECT d FROM Deadline d WHERE deadline_name = :name",
                Deadline.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    /**
     * select all deadlines
     * 
     * @return List<Deadline>
     */
    @Override
    public List<Deadline> selectAll() {
        TypedQuery<Deadline> query = em.createQuery("SELECT d FROM Deadline d", Deadline.class);
        return query.getResultList();
    }

}

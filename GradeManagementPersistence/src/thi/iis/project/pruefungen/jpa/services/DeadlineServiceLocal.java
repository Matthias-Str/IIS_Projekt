package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Deadline;

/**
 * Local Service Interface for Deadline Service
 * 
 * @author Katrin Krüger
 *
 */
@Local
public interface DeadlineServiceLocal {
    /**
     * create new Deadline
     * 
     * @param deadline
     */
    public void create(Deadline d);

    /**
     * select a deadline by name
     * 
     * @param name
     * @return Deadline
     */
    public Deadline selectByName(String name);

    /**
     * select all deadlines
     * 
     * @return List<Deadline>
     */
    public List<Deadline> selectAll();
}

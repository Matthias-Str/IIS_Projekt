package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Deadline;

/**
 * Local Service Interface for Deadline Service
 * @author Katrin Krüger
 *
 */
@Local
public interface DeadlineServiceLocal {
    public void create(Deadline d);
    public Deadline selectByName(String name);

}

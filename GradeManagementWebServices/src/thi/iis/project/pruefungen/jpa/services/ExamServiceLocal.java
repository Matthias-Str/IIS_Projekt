package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Exam;

/**
 * Local Service Interface for Exam Service
 * @author Katrin Krüger
 *
 */
@Local
public interface ExamServiceLocal {
    public Exam update(Exam e);
    public Exam selectByName(String name);
}

package thi.iis.project.pruefungen.jpa.services;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.StudentExam;

@Local
public interface StudentExamServiceLocal {
    public void create(StudentExam se);
}

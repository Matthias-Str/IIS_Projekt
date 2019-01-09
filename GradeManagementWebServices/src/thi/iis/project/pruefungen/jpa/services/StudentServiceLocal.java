package thi.iis.project.pruefungen.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thi.iis.project.pruefungen.jpa.entities.Student;

@Local
public interface StudentServiceLocal {
    public List<Student> selectAll();
}

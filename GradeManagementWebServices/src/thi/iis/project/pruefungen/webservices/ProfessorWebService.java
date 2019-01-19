package thi.iis.project.pruefungen.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.Professor;
import thi.iis.project.pruefungen.jpa.services.ProfessorService;

@WebService
public class ProfessorWebService
{
    @Inject
    ProfessorService professorService;
    
    
    
    public List<Professor> selectAll(){
        return professorService.selectAll();
    }
    
    public Professor selectById(int id){
        return professorService.selectById(id);
    }
}

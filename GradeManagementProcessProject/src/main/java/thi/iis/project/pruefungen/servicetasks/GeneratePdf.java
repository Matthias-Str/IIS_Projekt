package thi.iis.project.pruefungen.servicetasks;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

public class GeneratePdf implements JavaDelegate
{

    public static final String PDF_DIRECTORY = "/home/lars/Workspaces/Eclipse/JEE-Neon-R-Java/IIS_Projekt/GradeManagementProcessProject/src/main/resources/GradeSheets";
    public static final String TEX_TEMPLATE_PATH = "/home/lars/Workspaces/Eclipse/JEE-Neon-R-Java/IIS_Projekt/GradeManagementProcessProject/src/main/resources/GradeSheetTemplate.tex";
    public static final String TEX_LIST_ELEMENT_TEMPLATE_PATH = "/home/lars/Workspaces/Eclipse/JEE-Neon-R-Java/IIS_Projekt/GradeManagementProcessProject/src/main/resources/GradeSheetListElement.tex";
    
    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        System.out.print("Generating PDFs");
        
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        Student[] allStudents = studentWS.selectAllStudents();
        StudentExam[] allStudentExams = seWS.selectAll();
        
        for(int index = 0; index < allStudents.length; index++)
        {
            Student student = allStudents[index];
            
            String argument1_fullname = student.getFirstname()+" "+student.getLastname();
            String argument2_regNr = ""+student.getRegistrationNumber();
            String argument3_average = "Durchschnitt nicht verfügbar";
            
            List<String> argumentsX = new ArrayList<String>();
            
            Map<String,Double> subjectGrades = new HashMap<String,Double>();
            int succeededExams = 0;
            double succeededExamTotal = 0;
            
            for(StudentExam se : allStudentExams)
            {
                if(se.getRegistrationNumber() == student)
                {
                    String subjectname = se.getExamId().getSubject();
                    if(se.getParticipated() == false)
                    {
                        if(!subjectGrades.containsKey(subjectname))
                        {
                            subjectGrades.put(subjectname, 6.0);
                        }
                    }
                    else
                    {
                        
                        if(subjectGrades.containsKey(subjectname))
                        {
                            if(subjectGrades.get(subjectname)>4.0)
                            {
                                subjectGrades.put(subjectname, se.getGrade().doubleValue());
                                succeededExams++;
                                succeededExamTotal += se.getGrade().doubleValue();
                            }
                        }
                        else
                        {
                            double grade = se.getGrade().doubleValue();
                            subjectGrades.put(subjectname,grade);
                            if(grade<=4.0)
                            {
                                succeededExams++;
                                succeededExamTotal += se.getGrade().doubleValue();
                            }
                            
                        }
                    }
                }
            }
            
            if(succeededExams>0)
            {
                double average = succeededExamTotal/succeededExams;
                argument3_average = Double.toString(average);
            }
            
            if(allStudentExams.length==0)
            {
                //Cancel because there is not going to be a grade sheet for someone who hasn't been graded yet
                return;
            }
            
            String listelementString = "";
            List<String> allText = Files.readAllLines(Paths.get(TEX_LIST_ELEMENT_TEMPLATE_PATH));
            for(String s : allText)
            {
                listelementString+=s+"\n";
            }
            
            for(String subject : subjectGrades.keySet())
            {
                double grade = subjectGrades.get(subject);
                String filledTemplate = listelementString.replace("ARGUMENT_1", subject).replace("ARGUMENT_2", Double.toString(grade));
                
                argumentsX.add(filledTemplate);
            }
            
            String pdflatexString = "";
            List<String> allpdfText = Files.readAllLines(Paths.get(TEX_TEMPLATE_PATH));
            for(String s : allpdfText)
            {
                pdflatexString+=s+"\n";
            }
            
            
            pdflatexString = pdflatexString.replace("ARGUMENT_1", argument1_fullname);
            pdflatexString = pdflatexString.replace("ARGUMENT_2", argument2_regNr);
            pdflatexString = pdflatexString.replace("ARGUMENT_3", argument3_average);
            String combinedArgumentsX = "";
            for(String argX : argumentsX)
            {
                combinedArgumentsX+=argX;
            }
            pdflatexString = pdflatexString.replace("ARGUMENT_X", combinedArgumentsX);
            
            String texFilename = "Grades_"+argument2_regNr+".tex";
            String texPath = PDF_DIRECTORY+File.separatorChar+texFilename;  //Java doesn't even have a proper Path.Combine equivalent. Why are people still using it?
            //Call PDFLatex
            try (PrintWriter out = new PrintWriter(texFilename))
            {
                out.println(pdflatexString);
            }
            String[] params = new String[2];
            params[0] = "pdflatex";
            params[1] = texPath;
            Process p = Runtime.getRuntime().exec(params);
            p.waitFor();
            
            String pdfpath = texPath.replace(".tex", ".pdf");
            
    
            Map<Student,String> pdfmap = (Map<Student,String>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_PDF_MAP);
            pdfmap.put(student, pdfpath);
            
            execution.setVariable(ValueIdentifiers.VALUE_IDENTIFIER_PDF_MAP, pdfmap);
        }
        
    }
    
}

package thi.iis.project.pruefungen.servicetasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class PersistGrade implements JavaDelegate{

    public static final String GRADE_1_0_IDENTIFIER = "Value_Grade_1_0";
    public static final String GRADE_1_3_IDENTIFIER = "Value_Grade_1_3";
    public static final String GRADE_1_7_IDENTIFIER = "Value_Grade_1_7";
    public static final String GRADE_2_0_IDENTIFIER = "Value_Grade_2_0";
    public static final String GRADE_2_3_IDENTIFIER = "Value_Grade_2_3";
    public static final String GRADE_2_7_IDENTIFIER = "Value_Grade_2_7";
    public static final String GRADE_3_0_IDENTIFIER = "Value_Grade_3_0";
    public static final String GRADE_3_3_IDENTIFIER = "Value_Grade_3_3";
    public static final String GRADE_3_7_IDENTIFIER = "Value_Grade_3_7";
    public static final String GRADE_4_0_IDENTIFIER = "Value_Grade_4_0";
    public static final String GRADE_5_0_IDENTIFIER = "Value_Grade_Failed";
    
    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        StudentExam se = (StudentExam) execution.getVariableLocal(ValueIdentifiers.VALUE_IDENTIFIER_STUDENT_EXAM);
        String grade = (String) execution.getVariableLocal(ValueIdentifiers.VALUE_IDENTIFIER_INPUT_GRADE);
        
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        System.out.println("Recieved Grade: "+grade);
        
        switch(grade)
        {
            case GRADE_1_0_IDENTIFIER:
                se.setGrade(new BigDecimal(1.0));
                break;
            case GRADE_1_3_IDENTIFIER:
                se.setGrade(new BigDecimal(1.3));
                break;
            case GRADE_1_7_IDENTIFIER:
                se.setGrade(new BigDecimal(1.7));
                break;
            case GRADE_2_0_IDENTIFIER:
                se.setGrade(new BigDecimal(2.0));
                break;
            case GRADE_2_3_IDENTIFIER:
                se.setGrade(new BigDecimal(2.3));
                break;
            case GRADE_2_7_IDENTIFIER:
                se.setGrade(new BigDecimal(2.7));
                break;
            case GRADE_3_0_IDENTIFIER:
                se.setGrade(new BigDecimal(3.0));
                break;
            case GRADE_3_3_IDENTIFIER:
                se.setGrade(new BigDecimal(3.3));
                break;
            case GRADE_3_7_IDENTIFIER:
                se.setGrade(new BigDecimal(3.7));
                break;
            case GRADE_4_0_IDENTIFIER:
                se.setGrade(new BigDecimal(4.0));
                break;

            case GRADE_5_0_IDENTIFIER:
                se.setGrade(new BigDecimal(5.0));
                break;
        }
        
        se.setGradeChecked(false);
    }

}

package thi.iis.project.pruefungen.jpa.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.entities.Student;
import thi.iis.project.pruefungen.jpa.entities.StudentExam;
import thi.iis.project.pruefungen.jpa.services.StudentExamServiceLocal;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentWebService;

/**
 * Servlet to test studentExam Web Service
 * 
 * @author Katrin Krüger
 *
 */
@WebServlet("/StudentExamTestServlet")
public class StudentExamTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    StudentExamWebService seWS;
    @Inject
    ExamWebService eWS;
    @Inject
    StudentWebService sWS;
    @Inject
    StudentExamServiceLocal seSL;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentExamTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        // Create new StudentExam
        Exam e = eWS.selectByName("inf_m_itim_ws18");
        writer.println(e);
        Student s = sWS.selectByRegistrationName("pruefungsamt");
        writer.println(s.toString());
        StudentExam se = new StudentExam(null, e, null, false, false, s);
        writer.println(se.toString());
        seSL.create(se);
        
        // select by exam id and regsitration name
        StudentExam se2 = seWS.selectByRegistrationNameAndExamId("pruefungsamt", "inf_m_sesa_ws18");
        writer.println("by regName and ExId: " + se2.toString());
        
        // upadte
        se2.setDocumentUploaded(true);
        seWS.update(se2);
        se2 = seWS.selectByRegistrationNameAndExamId("pruefungsamt", "inf_m_sesa_ws18");
        writer.println("by regName and ExId: " + se2.toString());
        
        // select by exam id
        List<StudentExam> seList = seSL.selectByExamId("%inf_m_sesa_ws18%");
        writer.println("select by exam id");
        for(StudentExam se1 : seList){
            writer.println(se1.toString());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

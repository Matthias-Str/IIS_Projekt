package thi.iis.project.pruefungen.jpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class StudentExamTestServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    final PrintWriter writer = response.getWriter();
        // Create new StudentExam
	    Exam e = eWS.selectByName("inf_m_itim_ws18");
	    writer.println(e);
	    Student s = sWS.selectByRegistrationName("katrin");
	    writer.println(s.toString());
        StudentExam se = new StudentExam(null, e, null, false, false, s);
        writer.println(se.toString());
        seSL.create(se);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

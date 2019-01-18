package thi.iis.project.pruefungen.jpa.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thi.iis.project.pruefungen.jpa.entities.Exam;
import thi.iis.project.pruefungen.jpa.services.ExamServiceLocal;

/**
 * Servlet to test Exam Web Service
 * @author Katrin Krüger
 */
@WebServlet("/ExamTestServlet")
public class ExamTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	ExamServiceLocal examService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();

        // Select Exam by name
        Exam e = examService.selectByName("inf_m_kao_ws18");
        writer.println("Current Exam:" + e.toString());
        
        // update date
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY);
        try {
            cal.setTime(sdf.parse("01.12.2018 10:00:00"));
            date = sdf.parse("01.01.2018 10:00:00");
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        e.setDate(date);
        e = examService.update(e);
        writer.println("Current Exam:" + e.toString());
        
        Date d = examService.getFirstExamDate();
        writer.println("first exam date: " + d.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

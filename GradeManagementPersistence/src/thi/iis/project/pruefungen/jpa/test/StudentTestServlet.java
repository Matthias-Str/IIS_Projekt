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

import thi.iis.project.pruefungen.jpa.entities.Student;
import thi.iis.project.pruefungen.jpa.services.StudentServiceLocal;

/**
 * Servlet to test student web service
 * 
 * @author Katrin Krüger
 *
 */
@WebServlet("/StudentTestServlet")
public class StudentTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    StudentServiceLocal studentService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentTestServlet() {
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

        // Select Exam by name
        List<Student> studentList = studentService.selectAll();
        for (Student s : studentList) {
            writer.println(s.toString());
        }

        Student s = studentService.selectByRegistrationName("katrin");
        writer.println(s.toString());
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

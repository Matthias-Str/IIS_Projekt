package thi.iis.project.pruefungen.jpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thi.iis.project.pruefungen.jpa.entities.Deadline;
import thi.iis.project.pruefungen.jpa.services.DeadlineServiceLocal;

/**
 * Servlet implementation class DeadlineTestServlet
 */
@WebServlet("/DeadlineTestServlet")
public class DeadlineTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	DeadlineServiceLocal deadlineService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeadlineTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();

        // Select Deadline by name
        Deadline d = deadlineService.selectByName("start_registration");
        writer.println("start_registration:" + d.toString());
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

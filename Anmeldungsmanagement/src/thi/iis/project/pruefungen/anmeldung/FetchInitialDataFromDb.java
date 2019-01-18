package thi.iis.project.pruefungen.anmeldung;

import java.rmi.RemoteException;

import thi.iis.project.pruefungen.pojos.InputData;
import thi.iis.project.pruefungen.webservices.DeadlineWebService;
import thi.iis.project.pruefungen.webservices.DeadlineWebServiceProxy;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

/**
 * Helper to fetch all needed data for initialization from db
 * 
 * @author Katrin Krüger
 *
 */
public class FetchInitialDataFromDb {

    public FetchInitialDataFromDb() {

    }

    /**
     * request InputData from Database
     * 
     * @return InputData
     */
    public InputData getData() {
        InputData inputData = new InputData();

        // init webservices
        DeadlineWebService deadlineWS = new DeadlineWebServiceProxy().getDeadlineWebService();
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();

        try {
            // get and set deadline list
            inputData.setDeadlineList(deadlineWS.selectAll());
            // get and set student list
            inputData.setStudentList(studentWS.selectAllStudents());
            // get and set exam list
            inputData.setExamList(examWS.selectWhereIdContains("%ws18%"));
            // get and set number of students
            inputData.setNumberOfStudents(studentWS.selectAllStudents().length);
            // get and set first exam date
            inputData.setFirstExamDate(examWS.getFirstExamDate().getTime());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return inputData;
    }

}

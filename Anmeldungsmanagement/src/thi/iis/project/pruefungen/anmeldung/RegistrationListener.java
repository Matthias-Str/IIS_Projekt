package thi.iis.project.pruefungen.anmeldung;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.ExamWebService;
import thi.iis.project.pruefungen.webservices.ExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

/**
 * Listens to queue if a new registration of a students arrives
 * 
 * @author Katrin Krüger
 *
 */
public class RegistrationListener {
    Session session;

    public RegistrationListener() {

    }

    public RegistrationListener(Session session) {
        this.session = session;
    }

    /**
     * start the listener
     * 
     * @throws JMSException
     */
    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("preparedRegistration_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        // get message text and call persistence function
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        persistData(text);
                    }

                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };

        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);
    }

    /**
     * persist the student registration that is in message body
     * 
     * @param text
     */
    private void persistData(String text) {
        // init webservices
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        ExamWebService examWS = new ExamWebServiceProxy().getExamWebService();
        StudentExamWebService stundetExamWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        // get text as json Object
        try {
            JSONObject obj = new JSONObject(text);
            JSONObject message = obj.getJSONObject("anmeldung");
            // get username
            String username = message.getString("username");
            JSONObject registrationObject = message.getJSONObject("registrations");
            JSONArray registrationArray = registrationObject.getJSONArray("entry");
            for (int i = 0; i < registrationArray.length(); i++) {
                JSONObject curObject = registrationArray.getJSONObject(i);
                String examId = curObject.getString("key");
                Boolean registered = curObject.getBoolean("value");
                if (registered) {
                    // get exam
                    Student student = studentWS.selectByRegistrationName(username);
                    Exam e = examWS.selectByName(examId);
                    // add new entry in student_exam table if participation is
                    // true
                    StudentExam se = new StudentExam(false, e, null, false, false, student, 1);
                    stundetExamWS.create(se);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }

    }
}

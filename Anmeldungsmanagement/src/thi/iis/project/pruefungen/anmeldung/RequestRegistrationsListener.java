package thi.iis.project.pruefungen.anmeldung;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import thi.iis.project.pruefungen.pojos.RegistrationRequestReply;
import thi.iis.project.pruefungen.sender.SendObjectMessageToQueue;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class RequestRegistrationsListener {
    Session session;

    public RequestRegistrationsListener() {
        super();
    }

    public RequestRegistrationsListener(Session session) {
        super();
        this.session = session;
    }

    /**
     * start the listener
     * 
     * @throws JMSException
     */
    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("requestRegistrations_queue");

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
                        sendRegistrations(text);
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

    private void sendRegistrations(String text){
        StudentExamWebService studentExamWs = new StudentExamWebServiceProxy().getStudentExamWebService();
        RegistrationRequestReply reply = new RegistrationRequestReply();
        reply.setExamId(text);
        try {
            StudentExam[] studentExams = studentExamWs.selectByExamId("%" + text + "%");
            reply.setStudentExamList(studentExams);
            SendObjectMessageToQueue sotq = new SendObjectMessageToQueue();
            sotq.sendMessage(session, "studentExamsAnswer_queue", reply);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

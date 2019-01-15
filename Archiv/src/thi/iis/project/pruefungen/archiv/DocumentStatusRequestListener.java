package thi.iis.project.pruefungen.archiv;

import java.rmi.RemoteException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;


public class DocumentStatusRequestListener {
    private Session session;

    public DocumentStatusRequestListener() {

    }

    public DocumentStatusRequestListener(Session session) {
        super();
        this.session = session;
    }

    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("documentStatusRequestArchiv_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);
        

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        replyWithStatus(textMessage.getText());
                        
                    } catch (JMSException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };

        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);
    }
    
    private void replyWithStatus(String message){
        
        try {
            JSONObject json = new JSONObject(message);
            String registrationName = json.getString("registrationName");
            String examId = json.getString("examId");
            
            CheckDocumentStatus status = new CheckDocumentStatus(registrationName, examId);
            StudentExam studentExam = status.checkStatus();
            
            sendReply(studentExam);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    private void sendReply(StudentExam studentExam){
        JSONObject reply = new JSONObject();
        try {
            Student s = studentExam.getRegistrationNumber();
            reply.put("registrationName", s.getRegistrationName());
            Exam e = studentExam.getExamId();
            reply.put("examId", e.getExamId());
            reply.put("status", studentExam.getDocumentUploaded());
            
            System.out.println(reply.toString());
            
            Destination destination = session.createQueue("documentStatusReply_queue");
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage(reply.toString());
            producer.send(message);
            
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
}

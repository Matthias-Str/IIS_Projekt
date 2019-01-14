package thi.iis.project.pruefungen.management;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;


public class PersistDocumentListener {
    private Session session;

    public PersistDocumentListener() {

    }

    public PersistDocumentListener(Session session) {
        this.session = session;
    }

    public void startListener() throws JMSException {
        // Get or create queue
        Queue queue = session.createQueue("archive_queue");

        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);
        

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("nachricht erhalten");
                        persistDocument(textMessage.getText());
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
    
    private void persistDocument(String filename){
        StudentExamWebService studentExamWs = new StudentExamWebServiceProxy().getStudentExamWebService();
        String registrationName = getRegistrationNameFromFilename(filename);
        String examId = getExamIdFromFilename(filename);
        
        StudentExam studentExam = new StudentExam();
        try {
            studentExam = studentExamWs.selectByRegistrationNameAndExamId(registrationName, examId);
            studentExam.setDocumentUploaded(true);
            studentExamWs.update(studentExam);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private String getRegistrationNameFromFilename(String filename){
        String[] splitted = filename.split("_");
        System.out.println(splitted[0]);
        return splitted[0];
    }
    
    private String getExamIdFromFilename(String filename){
        String[] splitted = filename.split("_");
        String result = splitted[1];
        for(int i = 2; i < splitted.length; i++){
            result = result + "_" + splitted[i];
        }
        System.out.println(result);
        return result;
    }
}

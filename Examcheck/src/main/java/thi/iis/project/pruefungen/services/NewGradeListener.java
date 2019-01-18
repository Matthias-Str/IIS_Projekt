package thi.iis.project.pruefungen.services;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

public class NewGradeListener {
    Session session;
    
    public NewGradeListener(Session session) {
        this.session = session;
    }

    public void startListening(){
        try {
            Queue newGradeQueue = session.createQueue("newgrade_queue");
            MessageConsumer consumer = session.createConsumer(newGradeQueue);

            
            MessageListener listener = new MessageListener() {
                public void onMessage(Message message) {
                    if(message instanceof TextMessage){
                        try {
                            String text = ((TextMessage) message).getText();
                            StringReader reader = new StringReader(text);
                            StudentExam exam = JAXB.unmarshal(reader, StudentExam.class);
                            writeNewGrade(exam.getStudentExamId(), exam.getGrade());
                            MessageProducer producer = session.createProducer(message.getJMSReplyTo());
                            producer.send(session.createTextMessage("Saved new Grade!"));
                        } catch (JMSException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
            
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    private void writeNewGrade(int studentId, BigDecimal grade){
        StudentExamWebService studentExamWs = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        StudentExam exam;
        try {
            exam = studentExamWs.getById(studentId);
            exam.setGrade(grade);
            studentExamWs.create(exam);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

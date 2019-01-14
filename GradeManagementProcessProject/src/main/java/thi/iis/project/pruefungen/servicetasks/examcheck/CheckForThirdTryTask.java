package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class CheckForThirdTryTask implements JavaDelegate{
    
    private static final String THIRD_TRY_QUEUE = "thirdTryQueue";
    private static final String ACTIVEMQ_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination dest = session.createQueue(THIRD_TRY_QUEUE);
        
        Destination tempQueue = session.createTemporaryQueue();
        
        MessageProducer producer = session.createProducer(dest);
        MessageConsumer consumer = session.createConsumer(tempQueue);

        StringWriter writer = new StringWriter();
        
        Object student_exam = execution.getVariable("studentExam");
        
        if(student_exam != null){
            JAXB.marshal(student_exam, writer);
            
        } else {
            Exam testExam = new Exam();
            testExam.setExamId("inf_m_kao_ws18");
            
            Student testStudent = new Student();
            testStudent.setRegistrationName("matthias");
            
            StudentExam testStudentExam = new StudentExam();
            testStudentExam.setExamId(testExam);
            testStudentExam.setRegistrationNumber(testStudent);
       
            JAXB.marshal(testStudentExam, writer);       
        }
        
        TextMessage studenExamMessage = session.createTextMessage(writer.toString());
        
        studenExamMessage.setJMSReplyTo(tempQueue);
        
        producer.send(studenExamMessage);
        
        Message response = consumer.receive();
        
        if(response!=null){
            execution.setVariable("thirdTry", ((TextMessage)response).getText());
        }
        
        connection.close();
    }

}

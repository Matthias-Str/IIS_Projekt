package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.io.StringWriter;
import java.math.BigDecimal;

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
import thi.iis.project.pruefungen.webservices.Professor;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;

public class SaveNewGradeTask implements JavaDelegate {

    private static final String NEW_GRADE_QUEUE = "newgrade_queue";
    private static final String ACTIVEMQ_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        
//        Destination dest = session.createQueue(NEW_GRADE_QUEUE);
//        
//        Destination tempQueue = session.createTemporaryQueue();
//        
//        MessageProducer producer = session.createProducer(dest);
//        MessageConsumer consumer = session.createConsumer(tempQueue);
//
//        StringWriter writer = new StringWriter();
//        
        StudentExam student_exam = (StudentExam) execution.getVariable("studentExam");
//        
//        //neue Note setzen
        student_exam.setGrade(BigDecimal.valueOf(Double.valueOf((String) execution.getVariable("newGrade"))));
//        JAXB.marshal(student_exam, writer);
//        TextMessage studenExamMessage = session.createTextMessage(writer.toString());
//        studenExamMessage.setJMSReplyTo(tempQueue);
//        
//        producer.send(studenExamMessage);
//        
//        Message response = consumer.receive();
//        connection.close();
    }

}

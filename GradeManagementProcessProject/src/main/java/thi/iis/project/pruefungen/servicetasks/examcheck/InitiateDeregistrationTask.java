package thi.iis.project.pruefungen.servicetasks.examcheck;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.StudentExam;

/**
 * 
 * @author matthias strauss
 *
 */
public class InitiateDeregistrationTask implements JavaDelegate {
    
    private static final String DEREGISTRATION_QUEUE = "deregistrationQueue";
    private static final String ACTIVEMQ_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination dest = session.createQueue(DEREGISTRATION_QUEUE);
        
        MessageProducer producer = session.createProducer(dest);

        
        StudentExam studentExam = (StudentExam) execution.getVariable("studentExam");

        //Simple Message for demonstration purpose
        TextMessage txtMessage = session.createTextMessage(studentExam.getRegistrationNumber().getRegistrationName() + "has to be derigistered");
        
        System.out.println("Gonna send the textmessage: " + txtMessage);
        
        producer.send(txtMessage);
        connection.close();
    }

}

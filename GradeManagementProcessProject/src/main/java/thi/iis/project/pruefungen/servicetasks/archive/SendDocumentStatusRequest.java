package thi.iis.project.pruefungen.servicetasks.archive;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.pojos.DocumentStatusRequest;

/**
 * implementation of task "Anfrage senden, ob Dokument hochgeladen ist" sends
 * request message with registrationName and examId to get status of
 * student_exam entry
 * 
 * @author Katrin Krüger
 *
 */
public class SendDocumentStatusRequest implements JavaDelegate {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server
    // is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "documentStatusRequest_queue"; // Queue Name

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Connect to ActiveMQ
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);

        // create new DocumentStatusRequest
        execution.setVariable("registrationName", "pruefungsamt");
        execution.setVariable("examId", "inf_m_sesa_ws18");
        // Student student = (Student) execution.getVariable("student");
        // String registrationName = student.getRegistrationName();
        String registrationName = "pruefungsamt";
        // Exam exam = (Exam) execution.getVariable("exam");
        // String examId = exam.getExamId();
        String examId = "inf_m_sesa_ws18";
        DocumentStatusRequest request = new DocumentStatusRequest(registrationName, examId);

        // Create object messages
        ObjectMessage message = session.createObjectMessage(request);

        // Send message to queue
        producer.send(message);

        connection.close();

    }

}

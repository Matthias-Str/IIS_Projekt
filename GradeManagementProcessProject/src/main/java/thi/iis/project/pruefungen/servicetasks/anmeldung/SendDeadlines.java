package thi.iis.project.pruefungen.servicetasks.anmeldung;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Message Sending Task input: Values that pruefungsamt inserted in user form
 * output: text message (xml: DateList) sended to rawRegistrationDates_queue
 * 
 * @author Katrin Krüger
 *
 */
public class SendDeadlines implements JavaDelegate {
    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server
    // is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "rawRegistrationDates_queue"; // Queue Name

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // send data from usertask to anmeldungsmanagement
        sendToQueue(execution);

    }

    private void sendToQueue(DelegateExecution execution) throws JMSException {
        // connect to active mq
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);

        // get Variables
        // extract deadlines
        String start_registration = (String) execution.getVariable("input_start_registration");
        String end_registration = (String) execution.getVariable("input_end_registration");
        String grade_registration = (String) execution.getVariable("input_grade_registration");
        String announcement_date = (String) execution.getVariable("input_announcement_date");
        // extract examdates
        String examdate_kao = (String) execution.getVariable("input_examdate_kao");
        String examdate_iis = (String) execution.getVariable("input_examdate_iis");
        String examdate_sesa = (String) execution.getVariable("input_examdate_sesa");
        String examdate_itim = (String) execution.getVariable("input_examdate_itim");

        // add new Dates to MapMessage
        MapMessage message = session.createMapMessage();
        message.setObject("start_registration", start_registration);
        message.setObject("end_registration", end_registration);
        message.setObject("grade_registration", grade_registration);
        message.setObject("announcement_date", announcement_date);
        message.setObject("inf_m_kao_ws18", examdate_kao);
        message.setObject("inf_m_iis_ws18", examdate_iis);
        message.setObject("inf_m_sesa_ws18", examdate_sesa);
        message.setObject("inf_m_itim_ws18", examdate_itim);

        // Send message to queue
        producer.send(message);

        connection.close();
    }

}

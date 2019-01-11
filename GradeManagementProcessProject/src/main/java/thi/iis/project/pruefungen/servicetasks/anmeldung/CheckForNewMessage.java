package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.io.StringReader;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.beans.InputData;

public class CheckForNewMessage implements JavaDelegate {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable("msgReceived", false);
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(url);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("rawInitData_queue");
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);
            if (message != null) {
                    execution.setVariable("msgReceived", true);
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        StringReader reader = new StringReader(text);
                        InputData inputData = JAXB.unmarshal(reader, InputData.class);
                        // store input data in process variable
                        execution.setVariable("receivedMessage", true);
                        execution.setVariable("inputData", inputData.toString());
                    }
            }
            connection.close();

        } catch (JMSException e) {
            System.out.println("Error in Receiver");
            e.printStackTrace();
        }

    }
}

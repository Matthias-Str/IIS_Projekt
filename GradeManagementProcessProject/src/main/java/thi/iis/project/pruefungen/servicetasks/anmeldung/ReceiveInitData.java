package thi.iis.project.pruefungen.servicetasks.anmeldung;

import java.io.StringReader;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.beans.InputData;

public class ReceiveInitData implements JavaDelegate {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    InputData inputData = new InputData();
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //wait for message in queue
        // Getting JMS connection from the server
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Get or create queue
        Queue queue = session.createQueue("preparedInitData_queue");
        // create new consumer in session
        MessageConsumer consumer = session.createConsumer(queue);
 
        // extract text from message and convert to InputData
        
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            StringReader reader = new StringReader(text);
            inputData = JAXB.unmarshal(reader, InputData.class);
        }

        connection.close();
        
        // set inputDataObject as Variable
        execution.setVariable("inputData", inputData);
        
    }

}

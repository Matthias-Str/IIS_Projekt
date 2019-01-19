package thi.iis.project.pruefungen.servicetasks;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class TestWaitForDocDtatusReply implements JavaDelegate {
    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Getting JMS connection from the server

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        String examId = (String) execution.getVariableLocal("examId");
        String registrationName = (String) execution.getVariableLocal("registrationName");
        System.out.println(examId + "_" + registrationName);

        // Get or create queue
        Queue queue = session.createQueue(examId + "_" + registrationName);

        MessageConsumer consumer = session.createConsumer(queue);

        // execution.setVariableLocal("documentUploadStatus", "false");
        execution.setVariableLocal("msgReceived", false);
        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String result = evaluateMessageBody(execution, textMessage.getText());
                        System.out.println(result);
                        execution.setVariableLocal("msgReceived", true);
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };
        // Connect new Listener to Consumer
        consumer.setMessageListener(listener);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.close();

    }

    private String evaluateMessageBody(DelegateExecution execution, String text) {
        JSONObject context = new JSONObject(text);
        System.out.println(context.toString());

        if (context.getBoolean("status")) {
            return "true";
        } else {
            return "false";
        }

    }

}

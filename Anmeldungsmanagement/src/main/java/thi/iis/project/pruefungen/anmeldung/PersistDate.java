package thi.iis.project.pruefungen.anmeldung;

import java.io.IOException;
import java.sql.Connection;
import java.util.Queue;

public class PersistDate {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Get or create queue
        Queue queue = session.createQueue("Implementierung von Informationssystemen");

        MessageConsumer consumer = session.createConsumer(queue);

        // Create new MessageListener
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message: '" + textMessage.getText() + "'");
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
}

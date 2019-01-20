package thi.iis.project.pruefungen.servicetasks.examcheck;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

import thi.iis.project.pruefungen.servicetasks.ValueIdentifiers;
import thi.iis.project.pruefungen.webservices.Exam;
import thi.iis.project.pruefungen.webservices.Professor;
import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentExam;
import thi.iis.project.pruefungen.webservices.StudentExamWebService;
import thi.iis.project.pruefungen.webservices.StudentExamWebServiceProxy;

/**
 * 
 * @author matthias strauss
 *
 */
public class CheckForThirdTryTask implements JavaDelegate{
    
    private static final String THIRD_TRY_QUEUE = "thirdTryQueue";
    private static final String ACTIVEMQ_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        StudentExamWebService seWS = new StudentExamWebServiceProxy().getStudentExamWebService();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination dest = session.createQueue(THIRD_TRY_QUEUE);
        
        Destination tempQueue = session.createTemporaryQueue();
        
        MessageProducer producer = session.createProducer(dest);
        MessageConsumer consumer = session.createConsumer(tempQueue);

        StringWriter writer = new StringWriter();
        
        StudentExam student_exam = (StudentExam) execution.getVariable("studentExam");
        
        System.out.println("Now Checking Third Try for student_eaxme: " + student_exam);
        System.out.println(""+student_exam.getRegistrationNumber()+ ": grade: " + student_exam.getGrade());
        
        
        JAXB.marshal(student_exam, writer);
        
        TextMessage studenExamMessage = session.createTextMessage(writer.toString());
        
        studenExamMessage.setJMSReplyTo(tempQueue);
        
        producer.send(studenExamMessage);
        
        Message response = consumer.receive();
        
//        Map<String, Object> camundaVars = new HashMap<String, Object>();
//        camundaVars.put("examGrade", seWS.selectByRegistrationNameAndExamId(student_exam.getRegistrationNumber().getRegistrationName(), student_exam.getExamId().getExamId()).getGrade().doubleValue());
//        camundaVars.put("thirdTry", ((TextMessage) response).getText());
        
        boolean failedThirdTry = Boolean.parseBoolean(((TextMessage) response).getText()) && seWS.selectByRegistrationNameAndExamId(student_exam.getRegistrationNumber().getRegistrationName(), student_exam.getExamId().getExamId()).getGrade().doubleValue() >= 5d;
        
        execution.setVariable("thirdTry", failedThirdTry);
        System.out.println("ThirdTry: "+ ((TextMessage) response).getText());
        
//        execution.setVariablesLocal(camundaVars);
        
        
        connection.close();
    }

}

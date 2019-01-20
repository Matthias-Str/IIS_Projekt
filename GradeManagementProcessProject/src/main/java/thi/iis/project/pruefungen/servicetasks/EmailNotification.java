package thi.iis.project.pruefungen.servicetasks;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import thi.iis.project.pruefungen.webservices.Student;
import thi.iis.project.pruefungen.webservices.StudentWebService;
import thi.iis.project.pruefungen.webservices.StudentWebServiceProxy;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

//MHoepp
public class EmailNotification implements JavaDelegate {

    public static final String SENDER_EMAIL_IDENTIFIER = "pruefungsamtbpmn@gmx.de";
    public static final String SENDER_LOGIN_PASSWORD = "BpmnCreatesMoreProblemsThanItSolves";   //Me IRL: https://www.youtube.com/watch?v=2H7NZ0GNIIE
    public static final String SMTP_SERVER_ADDRESS = "mail.gmx.de";
    public static final int SMTP_PORT = 587;
    
    @Override
    public void execute(DelegateExecution execution) throws Exception
    {
        
        StudentWebService studentWS = new StudentWebServiceProxy().getStudentWebService();
        Student[] studentlist = studentWS.selectAllStudents();
        
        //List<Student> studentlist = (List<Student>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_STUDENTLIST);
        Map<Student,String> pdfmap = (Map<Student,String>) execution.getVariable(ValueIdentifiers.VALUE_IDENTIFIER_PDF_MAP);
        
        for(Student student : studentlist)
        {
            String mailDestination = student.getMail();
            String pdfpath = pdfmap.get(student);
            if( pdfpath == null )
            {
                continue;
            }
            String mailString = "Sehr geehrte/r Herr/Frau "+student.getLastname()+
                    "\n\nIhre Prüfungsergebnisse sind nun verfügbar \n Besuchen Sie das Studentenportal oder sehen Sie das Notenblatt an folgender Adresse ein:\n"+
                    "\n"+pdfpath+"\n"+
                    "\nGrüße,\n"+
                    "Ihr Prüfungsamt (BPMN Simulation)";
            
            
            if(!mailDestination.contains("demo"))
            {
                try{
                    Properties emailProperties = System.getProperties();
                    emailProperties = System.getProperties();
                    emailProperties.put("mail.smtp.port", Integer.toString(SMTP_PORT));
                    emailProperties.put("mail.smtp.auth", "true");
                    emailProperties.put("mail.smtp.user", SENDER_EMAIL_IDENTIFIER);
                    emailProperties.put("mail.smtp.password", SENDER_LOGIN_PASSWORD);
                    emailProperties.put("mail.smtp.host", SMTP_SERVER_ADDRESS);
                    emailProperties.put("mail.smtp.starttls.enable", "true");
                    
                    String headerSubject = "Notebekanntgabe";
                    
                    Session mailSession = Session.getInstance(emailProperties, new Authenticator()
                    {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(SENDER_EMAIL_IDENTIFIER,
                                    SENDER_LOGIN_PASSWORD);
                        }
                    });
        
                    Message message = new MimeMessage(mailSession);
                    InternetAddress addressTo = new InternetAddress(mailDestination);
                    message.setRecipient(Message.RecipientType.TO, addressTo);
                    message.setFrom(new InternetAddress(SENDER_EMAIL_IDENTIFIER));
                    message.setSubject(headerSubject);
                    message.setContent(mailString, "text/plain");
                    Transport.send(message);
                    System.out.println("Sent message");
                }catch(Exception e)
                {
                    System.out.println("could not establish connection");
                    System.out.println(mailString);
                }
            }
            
            
        }
    }

}

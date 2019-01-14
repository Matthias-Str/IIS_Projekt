package thi.iis.project.pruefungen.camel.pruefung;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import thi.iis.project.pruefungen.webservices.StudentExam;

public class ThirdTryJDBCProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        StudentExam studentExam = exchange.getIn().getBody(StudentExam.class);
    
        exchange.getIn().setBody("SELECT tries FROM (SELECT COUNT(subject) AS tries, subject, student_exam.exam_id FROM student_exam LEFT JOIN exam ON student_exam.exam_id = exam.exam_id WHERE registration_name = '"+studentExam.getRegistrationNumber().getRegistrationName()+"' AND participated = TRUE GROUP BY subject) AS tries_count WHERE tries_count.exam_id = '"+studentExam.getExamId().getExamId()+"';");
    }

}

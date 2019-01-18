package thi.iis.project.pruefungen.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the exam database table.
 * 
 * @author Katrin Krüger
 */
@Entity
@Table(name = "exam")
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Id
    @Column(name = "exam_id")
    private String examId;

    @JoinColumn(name = "professor_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Professor professorId;

    private String subject;

    public Exam() {
    }

    public Exam(String examId, Date date, Professor professorId, String subject) {
        super();
        this.examId = examId;
        this.date = date;
        this.professorId = professorId;
        this.subject = subject;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExamId() {
        return this.examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Professor getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(Professor professorId) {
        this.professorId = professorId;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Exam [date=" + date + ", examId=" + examId + ", professorId=" + professorId + ", subject=" + subject
                + "]";
    }

}
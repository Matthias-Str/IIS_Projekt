/**
 * StudentExamWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface StudentExamWebService extends java.rmi.Remote {
    public thi.iis.project.pruefungen.webservices.StudentExam[] selectAll() throws java.rmi.RemoteException;
    public void create(thi.iis.project.pruefungen.webservices.StudentExam arg0) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.StudentExam[] selectFromExam(thi.iis.project.pruefungen.webservices.Exam arg0) throws java.rmi.RemoteException;
}

/**
 * StudentExamWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface StudentExamWebService extends java.rmi.Remote {
    public thi.iis.project.pruefungen.webservices.StudentExam[] selectByExamId(java.lang.String arg0) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.StudentExam selectByRegistrationNameAndExamId(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public void create(thi.iis.project.pruefungen.webservices.StudentExam arg0) throws java.rmi.RemoteException;
    public void update(thi.iis.project.pruefungen.webservices.StudentExam arg0) throws java.rmi.RemoteException;
}

/**
 * ExamWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public interface ExamWebService extends java.rmi.Remote {
    public thi.iis.project.pruefungen.webservices.Exam selectByName(java.lang.String arg0) throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Exam[] selectAll() throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Exam[] selectWhereIdContains(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.util.Calendar getFirstExamDate() throws java.rmi.RemoteException;
    public thi.iis.project.pruefungen.webservices.Exam updateExamdate(java.lang.String arg0, java.util.Calendar arg1) throws java.rmi.RemoteException;
}

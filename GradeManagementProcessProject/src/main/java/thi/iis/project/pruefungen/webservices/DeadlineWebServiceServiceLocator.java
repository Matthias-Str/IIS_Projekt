/**
 * DeadlineWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public class DeadlineWebServiceServiceLocator extends org.apache.axis.client.Service implements thi.iis.project.pruefungen.webservices.DeadlineWebServiceService {

    public DeadlineWebServiceServiceLocator() {
    }


    public DeadlineWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DeadlineWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DeadlineWebServicePort
    private java.lang.String DeadlineWebServicePort_address = "http://localhost:8080/GradeManagementWebServices/DeadlineWebService";

    public java.lang.String getDeadlineWebServicePortAddress() {
        return DeadlineWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DeadlineWebServicePortWSDDServiceName = "DeadlineWebServicePort";

    public java.lang.String getDeadlineWebServicePortWSDDServiceName() {
        return DeadlineWebServicePortWSDDServiceName;
    }

    public void setDeadlineWebServicePortWSDDServiceName(java.lang.String name) {
        DeadlineWebServicePortWSDDServiceName = name;
    }

    public thi.iis.project.pruefungen.webservices.DeadlineWebService getDeadlineWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DeadlineWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDeadlineWebServicePort(endpoint);
    }

    public thi.iis.project.pruefungen.webservices.DeadlineWebService getDeadlineWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            thi.iis.project.pruefungen.webservices.DeadlineWebServiceServiceSoapBindingStub _stub = new thi.iis.project.pruefungen.webservices.DeadlineWebServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDeadlineWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDeadlineWebServicePortEndpointAddress(java.lang.String address) {
        DeadlineWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (thi.iis.project.pruefungen.webservices.DeadlineWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                thi.iis.project.pruefungen.webservices.DeadlineWebServiceServiceSoapBindingStub _stub = new thi.iis.project.pruefungen.webservices.DeadlineWebServiceServiceSoapBindingStub(new java.net.URL(DeadlineWebServicePort_address), this);
                _stub.setPortName(getDeadlineWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DeadlineWebServicePort".equals(inputPortName)) {
            return getDeadlineWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservices.pruefungen.project.iis.thi/", "DeadlineWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservices.pruefungen.project.iis.thi/", "DeadlineWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DeadlineWebServicePort".equals(portName)) {
            setDeadlineWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

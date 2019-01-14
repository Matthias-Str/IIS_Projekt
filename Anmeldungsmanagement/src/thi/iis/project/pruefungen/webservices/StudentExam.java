/**
 * StudentExam.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package thi.iis.project.pruefungen.webservices;

public class StudentExam  implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.Boolean documentUploaded;

    private thi.iis.project.pruefungen.webservices.Exam examId;

    private java.math.BigDecimal grade;

    private java.lang.Boolean gradeChecked;

    private java.lang.Boolean participated;

    private thi.iis.project.pruefungen.webservices.Student registrationNumber;

    private int studentExamId;

    public StudentExam() {
    }

    public StudentExam(
           java.lang.Boolean documentUploaded,
           thi.iis.project.pruefungen.webservices.Exam examId,
           java.math.BigDecimal grade,
           java.lang.Boolean gradeChecked,
           java.lang.Boolean participated,
           thi.iis.project.pruefungen.webservices.Student registrationNumber,
           int studentExamId) {
           this.documentUploaded = documentUploaded;
           this.examId = examId;
           this.grade = grade;
           this.gradeChecked = gradeChecked;
           this.participated = participated;
           this.registrationNumber = registrationNumber;
           this.studentExamId = studentExamId;
    }


    /**
     * Gets the documentUploaded value for this StudentExam.
     * 
     * @return documentUploaded
     */
    public java.lang.Boolean getDocumentUploaded() {
        return documentUploaded;
    }


    /**
     * Sets the documentUploaded value for this StudentExam.
     * 
     * @param documentUploaded
     */
    public void setDocumentUploaded(java.lang.Boolean documentUploaded) {
        this.documentUploaded = documentUploaded;
    }


    /**
     * Gets the examId value for this StudentExam.
     * 
     * @return examId
     */
    public thi.iis.project.pruefungen.webservices.Exam getExamId() {
        return examId;
    }


    /**
     * Sets the examId value for this StudentExam.
     * 
     * @param examId
     */
    public void setExamId(thi.iis.project.pruefungen.webservices.Exam examId) {
        this.examId = examId;
    }


    /**
     * Gets the grade value for this StudentExam.
     * 
     * @return grade
     */
    public java.math.BigDecimal getGrade() {
        return grade;
    }


    /**
     * Sets the grade value for this StudentExam.
     * 
     * @param grade
     */
    public void setGrade(java.math.BigDecimal grade) {
        this.grade = grade;
    }


    /**
     * Gets the gradeChecked value for this StudentExam.
     * 
     * @return gradeChecked
     */
    public java.lang.Boolean getGradeChecked() {
        return gradeChecked;
    }


    /**
     * Sets the gradeChecked value for this StudentExam.
     * 
     * @param gradeChecked
     */
    public void setGradeChecked(java.lang.Boolean gradeChecked) {
        this.gradeChecked = gradeChecked;
    }


    /**
     * Gets the participated value for this StudentExam.
     * 
     * @return participated
     */
    public java.lang.Boolean getParticipated() {
        return participated;
    }


    /**
     * Sets the participated value for this StudentExam.
     * 
     * @param participated
     */
    public void setParticipated(java.lang.Boolean participated) {
        this.participated = participated;
    }


    /**
     * Gets the registrationNumber value for this StudentExam.
     * 
     * @return registrationNumber
     */
    public thi.iis.project.pruefungen.webservices.Student getRegistrationNumber() {
        return registrationNumber;
    }


    /**
     * Sets the registrationNumber value for this StudentExam.
     * 
     * @param registrationNumber
     */
    public void setRegistrationNumber(thi.iis.project.pruefungen.webservices.Student registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    /**
     * Gets the studentExamId value for this StudentExam.
     * 
     * @return studentExamId
     */
    public int getStudentExamId() {
        return studentExamId;
    }


    /**
     * Sets the studentExamId value for this StudentExam.
     * 
     * @param studentExamId
     */
    public void setStudentExamId(int studentExamId) {
        this.studentExamId = studentExamId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StudentExam)) return false;
        StudentExam other = (StudentExam) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentUploaded==null && other.getDocumentUploaded()==null) || 
             (this.documentUploaded!=null &&
              this.documentUploaded.equals(other.getDocumentUploaded()))) &&
            ((this.examId==null && other.getExamId()==null) || 
             (this.examId!=null &&
              this.examId.equals(other.getExamId()))) &&
            ((this.grade==null && other.getGrade()==null) || 
             (this.grade!=null &&
              this.grade.equals(other.getGrade()))) &&
            ((this.gradeChecked==null && other.getGradeChecked()==null) || 
             (this.gradeChecked!=null &&
              this.gradeChecked.equals(other.getGradeChecked()))) &&
            ((this.participated==null && other.getParticipated()==null) || 
             (this.participated!=null &&
              this.participated.equals(other.getParticipated()))) &&
            ((this.registrationNumber==null && other.getRegistrationNumber()==null) || 
             (this.registrationNumber!=null &&
              this.registrationNumber.equals(other.getRegistrationNumber()))) &&
            this.studentExamId == other.getStudentExamId();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDocumentUploaded() != null) {
            _hashCode += getDocumentUploaded().hashCode();
        }
        if (getExamId() != null) {
            _hashCode += getExamId().hashCode();
        }
        if (getGrade() != null) {
            _hashCode += getGrade().hashCode();
        }
        if (getGradeChecked() != null) {
            _hashCode += getGradeChecked().hashCode();
        }
        if (getParticipated() != null) {
            _hashCode += getParticipated().hashCode();
        }
        if (getRegistrationNumber() != null) {
            _hashCode += getRegistrationNumber().hashCode();
        }
        _hashCode += getStudentExamId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StudentExam.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.pruefungen.project.iis.thi/", "studentExam"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentUploaded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentUploaded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("examId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "examId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.pruefungen.project.iis.thi/", "exam"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeChecked");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gradeChecked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "participated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registrationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.pruefungen.project.iis.thi/", "student"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("studentExamId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "studentExamId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

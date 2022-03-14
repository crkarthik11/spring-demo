package com.thinkxfactor.springdemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long studentID;

    @Column(name = "name", nullable = false)
    private String studentName;
    @Column(name = "age")
    private int studentAge;
    @Column(name = "gender")
    private String studentGender;
    @Column(name = "email", nullable = false, unique = true)
    private String studentEmailID;
    @Column(name = "contact", nullable = false, unique = true)
    private String studentContactNumber;
    @Column(name = "address")
    private String studentAddress;
    @Column(name = "dept")
    private String studentDepartment;
    @Column(name = "year")
    private int studentYear;
    @Column(name = "username", nullable = false, unique = true)
    private String studentUsername;
    @Column(name = "password", nullable = false)    
    private String studentPassword;
    
    public String getStudentUsername() {
        return studentUsername;
    }
    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }
    public String getStudentPassword() {
        return studentPassword;
    }
    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
    public long getStudentID() {
        return studentID;
    }
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    public String getStudentGender() {
        return studentGender;
    }
    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
    public String getStudentEmailID() {
        return studentEmailID;
    }
    public void setStudentEmailID(String studentEmailID) {
        this.studentEmailID = studentEmailID;
    }
    public String getStudentContactNumber() {
        return studentContactNumber;
    }
    public void setStudentContactNumber(String studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }
    public String getStudentAddress() {
        return studentAddress;
    }
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
    public String getStudentDepartment() {
        return studentDepartment;
    }
    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }
    public int getStudentYear() {
        return studentYear;
    }
    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }
}

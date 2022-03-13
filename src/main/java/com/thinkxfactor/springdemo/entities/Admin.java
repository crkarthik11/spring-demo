package com.thinkxfactor.springdemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
// import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tbl_admin")
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long adminID;


    @Column(name = "name", nullable = false)
    private String adminName;
    @Column(name = "age")
    private int adminAge;
    @Column(name = "gender")
    private String adminGender;
    @Column(name = "email", unique = true, nullable = false)
    private String adminEmailID;
    @Column(name = "contact", unique = true, nullable = false)
    private long adminContactNumber;
    @Column(name = "address")
    private String adminAddress;
    @Column(name = "username", unique = true, nullable = false)
    private String adminUsername;
    @Column(name = "password", nullable = false)
    private String adminPassword;
    
    public String getAdminUsername() {
        return adminUsername;
    }
    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    public long getAdminID() {
        return adminID;
    }
    public void setAdminID(long adminID) {
        this.adminID = adminID;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public int getAdminAge() {
        return adminAge;
    }
    public String getAdminGender() {
        return adminGender;
    }
    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }
    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }
    public String getAdminEmailID() {
        return adminEmailID;
    }
    public void setAdminEmailID(String adminEmailID) {
        this.adminEmailID = adminEmailID;
    }
    public long getAdminContactNumber() {
        return adminContactNumber;
    }
    public void setAdminContactNumber(long adminContactNumber) {
        this.adminContactNumber = adminContactNumber;
    }
    public String getAdminAddress() {
        return adminAddress;
    }
    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    
}

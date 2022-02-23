package com.thinkxfactor.springdemo.entities;

public class Admin {
    private String adminID;
    private String adminName;
    private int adminAge;
    private String adminGender;
    private String adminEmailID;
    private long adminContactNumber;
    private String adminAddress;
    
    public String getAdminID() {
        return adminID;
    }
    public void setAdminID(String adminID) {
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

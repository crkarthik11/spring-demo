package com.thinkxfactor.springdemo.entity;

import java.util.HashMap;

public class Student {

    private String name;
    private String department;
    private int rollNo;
    private int age;

    public Student(String name, String department, int rollNo, int age) {
        this.name = name;
        this.department = department;
        this.rollNo = rollNo;
        this.age = age;
    }

    public HashMap<String, Object> getData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("department", department);
        data.put("rollNo", rollNo);
        data.put("age", age);
        return data;
    }

    public int getRollNo() {
        return rollNo;
    }

}
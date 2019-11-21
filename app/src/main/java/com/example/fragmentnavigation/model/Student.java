package com.example.fragmentnavigation.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String fullName;
    private String age;
    private String gender;
    private String address;

    public Student(String fullname,String age , String gender, String address) {
        this.fullName = fullname;
        this.age = age;
        this.gender = gender;
        this.address = address;

    }

    public String getFullname() {
        return fullName;
    }

    public String getAge() {

        return age;
    }

    public String getGender() {

        return gender;
    }

    public String getAddress() {

        return address;
    }
}

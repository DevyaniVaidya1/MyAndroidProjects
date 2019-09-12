package com.example.challengeassignment;

public class UserClass {
    String name;
    String password;
    String phoneNumber;
    String email;

    public  UserClass(String string, String cursorString){
        this.name = name;
        this.password = password;
    }
    public UserClass(String email,String name, String phoneNumber,String password) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

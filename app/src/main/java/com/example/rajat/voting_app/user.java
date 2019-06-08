package com.example.rajat.voting_app;

public class user {
    String name;
    String emailid;
    String password;
    String pno;
    public user(){

    }

    public user(String name, String emailid, String password, String pno) {
        this.name = name;
        this.emailid = emailid;
        this.password = password;
        this.pno = pno;

    }

    public String getName() {
        return name;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public String getPno() {
        return pno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }
}

package com.example.rajat.voting_app;

public class candidates {
    String name;
    int count;
    String elec_id;
    public candidates(){

    }

    public candidates(String name, int count,String elec_id) {
        this.name = name;
        this.count = count;
        this.elec_id=elec_id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElec_id() {
        return elec_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setElec_id(String elec_id) {
        this.elec_id = elec_id;
    }
}

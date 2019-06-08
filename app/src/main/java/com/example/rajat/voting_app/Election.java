package com.example.rajat.voting_app;

public class Election {
    String id;
    String flag;
    String host_name;
    public Election(){

    }



    public Election(String id, String flag, String host_name) {
        this.id = id;
        this.flag=flag;
        this.host_name=host_name;

    }
    public String getHost_name() {
        return host_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}

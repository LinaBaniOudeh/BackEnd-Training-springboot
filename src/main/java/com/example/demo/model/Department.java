package com.example.demo.model;

public class Department {
    private int Depid;
    private  String Depname;

    public Department(int depid, String depname) {
        Depid = depid;
        Depname = depname;
    }

    public int getDepid() {
        return Depid;
    }

    public String getDepname() {
        return Depname;
    }

    public void setDepid(int depid) {
        Depid = depid;
    }

    public void setDepname(String depname) {
        Depname = depname;
    }
}


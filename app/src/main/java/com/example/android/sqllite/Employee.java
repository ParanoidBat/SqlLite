package com.example.android.sqllite;

public class Employee {
    private int id;
    private String name;
    private String paisa;

    Employee(){}

    Employee(int id, String name, String paisa){
        this.id= id;
        this.name= name;
        this.paisa= paisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaisa() {
        return paisa;
    }

    public void setPaisa(String paisa) {
        this.paisa = paisa;
    }
}

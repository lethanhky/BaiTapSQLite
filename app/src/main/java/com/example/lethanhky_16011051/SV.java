package com.example.lethanhky_16011051;

public class SV {
    private int id;
    private String name;
    private String class_name;
    private String subject;

    public SV() {
    }

    public SV(int id, String name, String class_name, String subject) {
        this.id = id;
        this.name = name;
        this.class_name = class_name;
        this.subject = subject;
    }

    public SV(String name, String class_name, String subject) {
        this.name = name;
        this.class_name = class_name;
        this.subject = subject;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

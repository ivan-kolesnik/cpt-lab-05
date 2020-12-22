package com.lab_05_db.dto;

public class Speciality {
    private int _id;
    private String _code;
    private String _name;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        _code = code;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @Override
    public String toString() {
        return "Speciality["
                + "id=" + _id
                + ", code=" + _code
                + ", name=" + _name
                + ']';
    }
}

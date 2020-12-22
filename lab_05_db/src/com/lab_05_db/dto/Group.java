package com.lab_05_db.dto;

public class Group {
    private int _id;
    private String _name;
    private Speciality _speciality;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Speciality getSpeciality() {
        return _speciality;
    }

    public void setSpeciality(Speciality speciality) {
        _speciality = speciality;
    }

    @Override
    public String toString() {
        return "Group["
                + "id=" + _id
                + ", name=" + _name
                + ", speciality=" + _speciality
                + ']';
    }
}

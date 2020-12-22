package com.lab_05_db.dto;

public class Student {
    private int _id;
    private String _firstName;
    private String _lastName;
    private Group _group;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public Group getGroup() {
        return _group;
    }

    public void setGroup(Group group) {
        _group = group;
    }

    @Override
    public String toString() {
        return "Student["
                + "id=" + _id
                + ", firstName=" + _firstName
                + ", lastName=" + _lastName
                + ", group=" + _group
                + ']';
    }
}

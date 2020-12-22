package com.lab_05_patterns.singleton;

public class President {
    private static President president;

    private String _firstName;
    private String _lastName;

    private President() {}

    private President(String firstName, String lastName) {
        _firstName = firstName;
        _lastName = lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public static President getPresident() {
        if (president == null) {
            president = new President();
        }

        return president;
    }

    public static void electPresident(String firstName, String lastName) {
        president = new President(firstName, lastName);
    }
}
package com.lab_05_db;

import com.lab_05_db.dao.postgresql.StudentDAO;
import com.lab_05_db.dto.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private final static String dbUrl = "jdbc:postgresql://localhost:5432/";
    private final static String dbName = "faculty";
    private final static String dbUser = "postgres";
    private final static String dbPassword = "postgres";

    public static void main(String[] args) {
        ConnectionManager cm;

        try {
            cm = new ConnectionManager(dbUrl + dbName, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Connection connection = cm.getConnection();

        StudentDAO studentDAO = new StudentDAO(connection);
        List<Student> list = studentDAO.findAll();

        printStudentList(list);

        Student studentToRemove = list.get(list.size() - 1);

        System.out.println("\nDeleting student with id = " + studentToRemove.getId() + "...");

        boolean res = studentDAO.delete(studentToRemove.getId());
        if (!res) {
            System.out.println("\n0 rows was affected.");
        }

        System.out.println("Deleted successfully.");
        printStudentList(list);
    }

    private static void printStudentList(List<Student> list) {
        if (!list.isEmpty()) {
            System.out.println("\nStudents:");
            for (Student student: list) {
                printStudent(student);
            }
        } else {
            System.out.println("No students found.");
        }
    }

    private static void printStudent(Student student) {
        String str =
                "\nStudent (id = " + student.getId() + ").\n" +
                "First name : " + student.getFirstName()                        + "\n" +
                "Last name  : " + student.getLastName()                         + "\n" +
                "Group      : " + student.getGroup().getName()                  + "\n" +
                "Speciality : " + student.getGroup().getSpeciality().getName();

        System.out.println(str);
    }
}

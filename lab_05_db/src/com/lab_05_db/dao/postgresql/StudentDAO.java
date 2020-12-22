package com.lab_05_db.dao.postgresql;

import com.lab_05_db.dao.DAO;
import com.lab_05_db.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDAO extends DAO<Integer, Student> {
    public StudentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(
                "SELECT" +
                    "    students.id         as \"students.id\","         +
                    "    students.first_name as \"students.first_name\"," +
                    "    students.last_name  as \"students.last_name\","  +
                    "    groups.id           as \"groups.id\","           +
                    "    groups.name         as \"groups.name\","         +
                    "    specialities.id     as \"specialities.id\","     +
                    "    specialities.code   as \"specialities.code\","   +
                    "    specialities.name   as \"specialities.name\""    +
                    "FROM students"                                       +
                    "    JOIN groups ON students.group_id = groups.id"    +
                    "    JOIN specialities ON groups.speciality_id = specialities.id;"
        );

        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Speciality speciality = new Speciality();
                speciality.setId(rs.getInt("specialities.id"));
                speciality.setCode(rs.getString("specialities.code"));
                speciality.setName(rs.getString("specialities.name"));

                Group group = new Group();
                group.setId(rs.getInt("groups.id"));
                group.setName(rs.getString("groups.name"));
                group.setSpeciality(speciality);

                Student student = new Student();
                student.setId(rs.getInt("students.id"));
                student.setFirstName(rs.getString("students.first_name"));
                student.setLastName(rs.getString("students.last_name"));
                student.setGroup(group);

                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return list;
    }

    @Override
    public boolean delete(Integer id) {
        PreparedStatement ps = getPrepareStatement("DELETE FROM students WHERE id=?");

        try {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return false;
    }
}

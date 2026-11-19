package com.ayush.SpringJdbcDemo.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ayush.SpringJdbcDemo.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/spring_student_db";
    String username = "root";
    String password = "Navneet105#";

    public void createStudent() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            //System.out.println("Connection established");

            Statement st = con.createStatement();

            String sql = "INSERT INTO students(name, email, age) " +
                    "VALUES ('Piyush', 'piyush@gmail.com' , 26)";

            int result = st.executeUpdate(sql);

            if (result == 1) {
                System.out.println("Create operation successful");
            } else {
                System.out.println("Create operation failed");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student, Long id) {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            //System.out.println("Connection established");

            String name = student.getName();
            String email = student.getEmail();
            int age = student.getAge();

            String sql = """
                        update students set name = ?, 
                                            email = ?,
                                            age = ? 
                                        where id = ?
                    """;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            ps.setLong(4, id);

            int result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("Update operation successful");
            } else {
                System.out.println("Update operation failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id) {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            //System.out.println("Connection established");

            Statement st = con.createStatement();

            String sql = "DELETE FROM students WHERE id = " + id;

            int result = st.executeUpdate(sql);

            if (result == 1) {
                System.out.println("Delete operation successful");
            } else {
                System.out.println("Delete operation failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            String sql = "SELECT id, name, email, age FROM students";
            ResultSet rs = st.executeQuery(sql);

            List<Student> students = new ArrayList<>();

            while (rs.next()) {
                Student s = mapRow(rs);
                students.add(s);
            }

            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Student mapRow(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getLong(1));
        s.setName(rs.getString(2));
        s.setEmail(rs.getString(3));
        s.setAge(rs.getInt(4));
        return s;
    }

    public void createPreparedStudent(Student s) {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = """
                            
                    INSERT INTO students(name, email, age)
                            VALUES(?, ?, ?)
                            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setInt(3, s.getAge());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 1){
                System.out.println("Student created successfully" );
            }
            else {
                System.out.println("Student  failed"); }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Student getStudentById(Long id) {

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            String sql = """
                    SELECT id,name,email,age from students where id = ?
                    """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            Student student = null;

            while (rs.next()) {
                student = mapRow(rs);
//                System.out.println(student.toString());
            }
            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

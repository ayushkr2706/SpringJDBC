package com.ayush.SpringJdbcDemo.repository;

import java.util.List;

import com.ayush.SpringJdbcDemo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student){
        String sql = """
                INSERT INTO students(name, email, age)
                VALUES(?, ?, ?)
                """;

        int rowAffected = jdbcTemplate.update(sql,
                student.getName(), student.getEmail(), student.getAge());

        if(rowAffected == 1){
            System.out.println("Student created successfully");
        }
        else {
            System.out.println("Student creation failed");
        }
    }

    public void updateStudent(Student student, Long id){

        String sql = """
                UPDATE students SET name = ?,
                email = ?,
                age = ?
                WHERE id = ?
                """;

        int rowAffected = jdbcTemplate.update(sql,
                student.getName(), student.getEmail(), student.getAge(), id);

        if(rowAffected == 1){
            System.out.println("Student updation successful");
        }
        else {
            System.out.println("Student updation failed");
        }
    }

    public void deleteStudent(Long id){

        String sql = """
                DELETE FROM students WHERE id = ?
                """;

        int rowAffected = jdbcTemplate.update(sql, id);

        if(rowAffected == 1){
            System.out.println("Student deletion successful");
        }
        else{
            System.out.println("Student deletion failed");
        }
    }

    public List<Student> getAllStudents(){

        String sql = """
                SELECT id, name, email, age FROM students
               """;
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());

        return students;

    }

    public Student getStudentById(Long id){

        String sql = """
                SELECT id, name, email, age FROM students WHERE id = ?
                """;

        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);

        return student;
    }

//    String url = "jdbc:mysql://localhost:3306/spring_student_db";
//    String username = "root";
//    String password = "";
//
//    public void createStudent() {
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            //System.out.println("Connection established");
//
//            Statement st = con.createStatement();
//
//            String sql = "INSERT INTO students(name, email, age) " +
//                    "VALUES ('Piyush', 'piyush@gmail.com' , 26)";
//
//            int result = st.executeUpdate(sql);
//
//            if (result == 1) {
//                System.out.println("Create operation successful");
//            } else {
//                System.out.println("Create operation failed");
//            }
//
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateStudent(Student student, Long id) {
//
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            //System.out.println("Connection established");
//
//            String name = student.getName();
//            String email = student.getEmail();
//            int age = student.getAge();
//
//            String sql = """
//                        update students set name = ?,
//                                            email = ?,
//                                            age = ?
//                                        where id = ?
//                    """;
//            PreparedStatement ps = con.prepareStatement(sql);
//
//            ps.setString(1, name);
//            ps.setString(2, email);
//            ps.setInt(3, age);
//            ps.setLong(4, id);
//
//            int result = ps.executeUpdate();
//
//            if (result == 1) {
//                System.out.println("Update operation successful");
//            } else {
//                System.out.println("Update operation failed");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteStudent(Long id) {
//
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            //System.out.println("Connection established");
//
//            Statement st = con.createStatement();
//
//            String sql = "DELETE FROM students WHERE id = " + id;
//
//            int result = st.executeUpdate(sql);
//
//            if (result == 1) {
//                System.out.println("Delete operation successful");
//            } else {
//                System.out.println("Delete operation failed");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Student> getAllStudents() {
//
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            Statement st = con.createStatement();
//
//            String sql = "SELECT id, name, email, age FROM students";
//            ResultSet rs = st.executeQuery(sql);
//
//            List<Student> students = new ArrayList<>();
//
//            while (rs.next()) {
//                Student s = mapRow(rs);
//                students.add(s);
//            }
//
//            return students;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    public Student mapRow(ResultSet rs) throws SQLException {
//        Student s = new Student();
//        s.setId(rs.getLong(1));
//        s.setName(rs.getString(2));
//        s.setEmail(rs.getString(3));
//        s.setAge(rs.getInt(4));
//        return s;
//    }
//
//    public void createPreparedStudent(Student s) {
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//            String sql = """
//
//                    INSERT INTO students(name, email, age)
//                            VALUES(?, ?, ?)
//                            """;
//
//            PreparedStatement ps = con.prepareStatement(sql);
//
//            ps.setString(1, s.getName());
//            ps.setString(2, s.getEmail());
//            ps.setInt(3, s.getAge());
//
//            int rowsAffected = ps.executeUpdate();
//            if(rowsAffected == 1){
//                System.out.println("Student created successfully" );
//            }
//            else {
//                System.out.println("Student  failed"); }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public Student getStudentById(Long id) {
//
//        try {
//            Connection con = DriverManager.getConnection(url, username, password);
//
//            String sql = """
//                    SELECT id,name,email,age from students where id = ?
//                    """;
//
//            PreparedStatement ps = con.prepareStatement(sql);
//
//            ps.setLong(1, id);
//
//            ResultSet rs = ps.executeQuery();
//
//            Student student = null;
//
//            while (rs.next()) {
//                student = mapRow(rs);
////                System.out.println(student.toString());
//            }
//            return student;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}

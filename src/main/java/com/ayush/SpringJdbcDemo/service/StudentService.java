package com.ayush.SpringJdbcDemo.service;

import com.ayush.SpringJdbcDemo.model.Student;
import com.ayush.SpringJdbcDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        studentRepository.createPreparedStudent(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(Long id){
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student, student.getId());
    }

    public void deleteStudent(Long id){
        studentRepository.deleteStudent(id);
    }
}

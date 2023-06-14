package com.example.mybatis.mapper;

import com.example.mybatis.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentXmlMapper {
    // INSERT
    void insertStudent(Student student);
    void insertStudentKeys(Student student);

    // SELECT
    List<Student> selectStudentAll();
    Student selectStudent(Long id);

    // UPDATE
    void updateStudent(Student student);

    // DELETE
    void deleteStudent(Long id);

    // select with optional
    Optional<Student> selectStudentOptional(Long id);

    // dynamic SQL
    List<Student> findByFields(Student student);
    void insertStudentBatch(List<Student> students);
}

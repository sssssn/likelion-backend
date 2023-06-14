package com.example.mybatis.mapper;

import com.example.mybatis.mapper.provider.StudentProvider;
import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper // MyBatis 가 Mapper 가 붙은 클래스를 데이터베이스 통신에 사용할 준비
public interface StudentMapper {
    // INSERT INTO students (name, age, phone, email)
    // VALUE (?, ?, ?, ?);
    @Insert("INSERT INTO students (name, age, phone, email) " +
            "VALUES (#{name}, #{age}, #{phone}, #{email})")
    void insertStudent(Student student);

    @Insert("INSERT INTO students(name, age, phone, email)\n" +
            "VALUES(#{name}, #{age}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudentKeys(Student student);

    // SELECT * FROM students; 를 실행할 메서드를 만드는데
    // 복수개의 Students 를 반환하게 하는 return 타입 -> List<Student>
    @Select("SELECT * FROM students")
    List<Student> selectStudentAll();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student selectStudent(Long id);

    // UPDATE
    @Update("UPDATE students SET " +
            "name = #{name}, " +
            "age = #{age}, " +
            "phone = #{phone}," +
            "email = #{email} " +
            "WHERE id = #{id}")
    void updateStudent(Student student);

    // DELETE
    @Delete("DELETE FROM students " +
            "WHERE id = #{id}")
    void deleteStudent(Long id);

    // select with optional
    @Select("SELECT * FROM students WHERE id = #{id}")
    Optional<Student> selectStudentOptional(Long id);

    // dynamic SQL
    @SelectProvider(type = StudentProvider.class, method = "findByFields")
    List<Student> findByFields(Student student);

    // dml SELECT, INSERT, UPDATE, DELETE
//    @Insert()
//    @Update()
//    @Delete()
}

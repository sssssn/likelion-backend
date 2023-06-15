package com.example.jpa.dto;

import com.example.jpa.entities.StudentEntity;
import lombok.Data;

import javax.swing.text.html.parser.Entity;

@Data
public class StudentDto {
    private Long id; // Entity.id
    private String name; // Entity.name
    private String email; // Entity.email

    // 정적 팩토리 메서드 패턴 (static factory method pattern)
    public static StudentDto fromEntity(StudentEntity studentEntity) {
        StudentDto dto = new StudentDto();
        dto.setId(studentEntity.getId());
        dto.setName(studentEntity.getName());
        dto.setEmail(studentEntity.getEmail());
        return dto;
    }
}

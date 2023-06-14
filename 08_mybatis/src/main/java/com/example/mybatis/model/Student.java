package com.example.mybatis.model;

import lombok.*;

//@Getter
//@Setter
//@RequiredArgsConstructor
//@ToString
//@EqualsAndHashCode
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
}
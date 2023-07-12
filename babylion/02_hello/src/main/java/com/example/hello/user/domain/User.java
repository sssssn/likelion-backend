package com.example.hello.user.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Table(name = "users") // H2 사용 시 user -> users
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}

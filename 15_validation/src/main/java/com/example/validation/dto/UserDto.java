package com.example.validation.dto;

import com.example.validation.constraints.annotations.Blacklist;
import com.example.validation.constraints.annotations.EmailWhitelist;
import com.example.validation.constraints.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;

    @NotBlank // 비어있지 않다
    @Size(min = 8, message = "최소 8글자 이어야 합니다. (최댓값도 있는데 그거 다 채워 넣으시진 못할걸요?")
    @Blacklist(blacklist = {"malware.good", "trojan.jjang"})
    private String username;
    @Email // 형식이 이메일이어야 한다
    // 이메일이 지정된 도메인 (gmail.com 등) 이도록 검증하는 어노테이션을 만들어 봅시다.
    @EmailWhitelist
    private String email;
    @NotNull // null 이 아니어야 한다
    @Phone010 // 010으로 시작하는 번호 형식이어야 한다
    private String phone;

    @NotNull
    @Min(
            value = 14,
            message = "14세 미만은 부모님의 동의가 필요합니다."
    ) // 최솟값 이상이어야 한다
    private Integer age;

    @Future(message = "미래의 시간까지 유효해야 합니다.") // 현재 시간보다 미래이어야 한다
    private LocalDate validUntil;

    @NotNull // notNullString 이 null 이 아닌지만 검증
    private String notNullString;
    @NotEmpty // notEmptyString 이 길이가 0이 아닌지만 검증
    private String notEmptyString;
    @NotBlank // notBlankString 이 공백 문자로만 이루어지지 않았는지 검증
    private String notBlankString;
}

/*
{
    "username": "sn.dev",
    "email": "sn.dev@gmail.com",
    "phone": "010-1004-1004"
}
 */

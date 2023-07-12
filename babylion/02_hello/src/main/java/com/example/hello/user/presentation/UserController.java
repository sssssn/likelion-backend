package com.example.hello.user.presentation;

import com.example.hello.user.application.UserCreateRequestDto;
import com.example.hello.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public void create(@RequestBody UserCreateRequestDto request) {
        UserCreateRequestDto dto = new UserCreateRequestDto (
                request.getNickname()
        );
        userService.createUser(dto);
    }
}

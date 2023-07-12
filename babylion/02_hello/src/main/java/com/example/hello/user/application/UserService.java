package com.example.hello.user.application;

import com.example.hello.user.domain.User;
import com.example.hello.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserCreateRequestDto requestDto) {
        boolean isExists = userRepository.existsByNickname(requestDto.getNickname());
        if(isExists) throw new RuntimeException("닉네임 중복 입니다.");
        User user = new User(
                requestDto.getNickname()
        );
        userRepository.save(user);
    }
}

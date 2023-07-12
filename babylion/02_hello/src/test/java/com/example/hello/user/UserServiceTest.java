package com.example.hello.user;

import com.example.hello.user.application.UserCreateRequestDto;
import com.example.hello.user.application.UserService;
import com.example.hello.user.domain.User;
import com.example.hello.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock // Mock 객체임을 나타내는 어노테이션
    private UserRepository userRepository;

    @InjectMocks // 해당 객체가 필요로 하는 의존성을 정의한 Mock 객체로 전달하는 어노테이션
    private UserService userService;
    // 실제 데이터베이스와 소통하는 userRepository 가 아닌 가짜가 할당됨

    @Nested
    @DisplayName("생성")
    class Create {
        @Test
        @DisplayName("유저 생성")
        void createUser() {
            // given
//            // 생성할 user
//            User user = new User(
//                    "선녀"
//            );
            // 생성될 user
            User saveUserResponse = new User(
                    1,
                    "선녀"
            );
            // Mock 객체의 행동 정의
            // any: 어떤 값이 오든
//            when(userRepository.save(user)).thenReturn(saveUserResponse);
            when(userRepository.save(any())).thenReturn(saveUserResponse);

            // when
            UserCreateRequestDto requestDto = new UserCreateRequestDto(
                    "선녀"
            );
            userService.createUser(requestDto);
        }

        @Test
        @DisplayName("이름이 중복이면 예외를 뱉는가")
        void duplicateNickname() {
            // given
            when(userRepository.existsByNickname(anyString())).thenReturn(true);

            // when, then
            UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto("선녀");
            assertThrows(RuntimeException.class, () -> userService.createUser(userCreateRequestDto));
        }
    }

    @Nested
    @DisplayName("조회")
    class Read {
        @Test
        @DisplayName("유저 조회")
        void readUser() {

        }
    }

    @Nested
    @DisplayName("수정")
    class Update {
        @Test
        @DisplayName("유저 수정")
        void updateUser() {

        }
    }
}
